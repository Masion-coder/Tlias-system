package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.tlias.module.Emp;
import com.tlias.module.EmpQueryParam;

/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)"
            + " values(#{username}, #{name}, #{gender}, {#phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);
}
