package com.tlias.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.EmpExprMapper;
import com.tlias.mapper.EmpMapper;
import com.tlias.model.Emp;
import com.tlias.model.EmpExpr;
import com.tlias.model.EmpLog;
import com.tlias.model.EmpQueryParam;
import com.tlias.model.PageResult;
import com.tlias.service.EmpLogService;
import com.tlias.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /*
     * 注意：
     * 1.使用PageHelper时， 定义的sql结尾不能加分号
     * 2.PageHelper仅仅能对紧跟在其后的第一个sql进行分页处理，后续的sql不会被分页处理
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        Page<Emp> p = (Page<Emp>) empMapper.list(empQueryParam);
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = { Exception.class }) // 事务管理 - 默认情况下，只拦截运行时异常RuntimeException
    @Override
    public void save(Emp emp) {
        try {
            // 1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 1.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                // 遍历集合，设置员工id
                exprList.forEach(empExpr -> {
                    empExpr.setId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void delete(List<Integer> ids) {
        // 1.批量删除员工基本信息
        empMapper.deleteByIds(ids);

        // 2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        Emp emp = empMapper.getById(id);
        List<EmpExpr> exprList = empExprMapper.getByEmpId(id);
        emp.setExprList(exprList);
        return emp;
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public void update(Emp emp) {
        // 1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2.根据ID修改员工的工作经历信息
        // 2.1先根据员工ID，删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        // 2.2再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> {
                expr.setId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
}
