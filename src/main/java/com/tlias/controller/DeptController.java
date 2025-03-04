package com.tlias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.module.Dept;
import com.tlias.module.Result;
import com.tlias.service.DeptService;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    
    @GetMapping("/depts")
    public Result findAll() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList =  deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("删除部门:" + id);
        deptService.deleteById(id);
        return Result.success();
    }
}
