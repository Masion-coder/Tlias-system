package com.tlias.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Result page(String name, Integer gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询:{}, {}, {}, {}, {}, {}", name, gender, begin, end, page, pageSize);
        PageResult<Emp> pageResult = empService.page(name, gender, begin, end, page, pageSize);
        return Result.success(pageResult);
    }
}
