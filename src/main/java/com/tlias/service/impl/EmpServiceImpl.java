package com.tlias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlias.mapper.EmpMapper;
import com.tlias.module.Emp;
import com.tlias.module.PageResult;
import com.tlias.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 1. 查询总记录数
        Long total = empMapper.count();
        // 2. 查询分页数据
        List<Emp> rows = empMapper.list((page - 1) * pageSize, pageSize);
        // 3. 封装到 PageResult 对象中并返回
        return new PageResult<Emp>(total, rows);
    }
    
}
