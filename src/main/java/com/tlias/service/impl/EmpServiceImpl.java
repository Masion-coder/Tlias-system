package com.tlias.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.EmpMapper;
import com.tlias.module.Emp;
import com.tlias.module.EmpQueryParam;
import com.tlias.module.PageResult;
import com.tlias.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /*
     * 注意：
     *      1.使用PageHelper时， 定义的sql结尾不能加分号
     *      2.PageHelper仅仅能对紧跟在其后的第一个sql进行分页处理，后续的sql不会被分页处理
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        Page<Emp> p = (Page<Emp>) empMapper.list(empQueryParam);
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Emp emp) {
        // 1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 1.保存员工工作经历信息
    }
    
}
