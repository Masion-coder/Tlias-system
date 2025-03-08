package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tlias.module.Emp;


/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    @Select("SELECT e.*, d.name deptName FROM emp e LEFT JOIN dept d ON e.dept_id = d.id order by e.update_time desc")
    public List<Emp> list();
}
