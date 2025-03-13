package com.tlias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.model.Emp;
import com.tlias.model.EmpQueryParam;
import com.tlias.model.PageResult;
import com.tlias.model.Result;
import com.tlias.service.EmpService;

import lombok.extern.slf4j.Slf4j;

/*
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping()
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工");
        empService.save(emp);
        return Result.success();
    }

    /*
     * 删除员工 - 数组接收
     */
    // @DeleteMapping
    // public Result delete(Integer[] ids) {
    //     log.info("根据id批量删除员工:{}", Arrays.toString(ids));
    //     return Result.success();
    // }
    
    /*
     * 删除员工 - list集合接收(要加@RequestParam注解)
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("根据id批量删除员工:{}", ids.toString());
        return Result.success();
    }
}
