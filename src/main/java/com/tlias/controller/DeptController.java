package com.tlias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.module.Dept;
import com.tlias.module.Result;
import com.tlias.service.DeptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    
    @GetMapping
    public Result findAll() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList =  deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println("删除部门:" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("添加部门:" + dept);
        deptService.add(dept);
        return Result.success();
    }
    
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        System.out.println("根据id查询部门:" + id);
        return Result.success(deptService.getById(id));
    }
    
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("更新部门:" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
