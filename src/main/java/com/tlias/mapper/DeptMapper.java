package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tlias.module.Dept;

@Mapper
public interface DeptMapper {
    /*
     * 查询所有部门
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();
}
