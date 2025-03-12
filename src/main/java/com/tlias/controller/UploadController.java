package com.tlias.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlias.module.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@RestController
public class UploadController {
    
    @PostMapping("/upload")
    public Result postMethodName(String name, Integer age, MultipartFile file) {
        //TODO: process POST request
        log.info("接收到的参数:{}, {}, {}", name, age, file);
        return null;
    }
    
}
