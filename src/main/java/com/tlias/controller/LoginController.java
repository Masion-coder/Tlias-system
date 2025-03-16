package com.tlias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlias.model.Emp;
import com.tlias.model.LoginInfo;
import com.tlias.model.Result;
import com.tlias.service.EmpService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result postMethodName(@RequestBody Emp emp) {
        log.info("登录：{}", emp);
        LoginInfo info = empService.login(emp);
        if (info != null) {
            return Result.success(info);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
