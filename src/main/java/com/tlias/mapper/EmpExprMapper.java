package com.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tlias.module.EmpExpr;

/*
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /*
     * 批量保存员工工作经历信息
     */
    public void insertBatch(List<EmpExpr> exprList);
}
