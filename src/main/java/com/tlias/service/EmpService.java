package com.tlias.service;

import java.util.List;

import com.tlias.model.Emp;
import com.tlias.model.EmpQueryParam;
import com.tlias.model.LoginInfo;
import com.tlias.model.PageResult;

public interface EmpService {
    /*
     * 分页查询
     * 
     * @param page 页码
     * 
     * @param pageSize 每页记录数
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
