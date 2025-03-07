package com.tlias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.module.Emp;
import com.tlias.module.PageResult;
import com.tlias.module.Result;
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
    public Result page(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询:{},{}", page, pageSize);
        PageResult<Emp> pageResult = empService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
