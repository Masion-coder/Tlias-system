package com.tlias.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.EmpMapper;
import com.tlias.module.Emp;
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
    public PageResult<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<Emp> p = (Page<Emp>) empMapper.list(name, gender, begin, end);
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
    
}
