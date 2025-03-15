package com.tlias.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tlias.model.Emp;
import com.tlias.model.EmpQueryParam;

/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)"
            + " values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);

    public void deleteByIds(List<Integer> ids);

    @Select("select * from emp where id = #{id}")
    public Emp getById(Integer id);

    public void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();
}
