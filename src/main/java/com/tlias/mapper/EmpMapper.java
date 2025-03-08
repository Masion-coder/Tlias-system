package com.tlias.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tlias.module.Emp;


/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
}
