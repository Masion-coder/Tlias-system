package com.tlias.service;

import java.time.LocalDate;

import com.tlias.module.Emp;
import com.tlias.module.PageResult;

public interface EmpService {
    /*
     * 分页查询
     * 
     * @param page 页码
     * 
     * @param pageSize 每页记录数
     */
    PageResult<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

}
