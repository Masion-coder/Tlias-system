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
    // 方式一：使用@Results注解
    // @Results({
    //     @Result(column = "create_time", property = "createTime"),
    //     @Result(column = "update_time", property = "updateTime")
    // })
    // 方式二：起别名
    // @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")
    // 方式三：开启驼峰下划线自动映射
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();
}
