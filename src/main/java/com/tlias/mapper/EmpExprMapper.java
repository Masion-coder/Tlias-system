package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tlias.model.EmpExpr;

/*
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /*
     * 批量保存员工工作经历信息
     */
    public void insertBatch(List<EmpExpr> exprList);

    public void deleteByEmpIds(List<Integer> empIds);

    @Select("select * from emp_expr where emp_id = #{empId}")
    public List<EmpExpr> getByEmpId(Integer empId);
}
