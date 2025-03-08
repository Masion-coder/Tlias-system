package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tlias.module.Emp;
import com.tlias.module.EmpQueryParam;

/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);
}
