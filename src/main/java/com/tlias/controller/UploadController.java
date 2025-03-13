package com.tlias.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlias.model.Result;
import com.tlias.service.UploadService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        log.info("文件上传:{}", file.getOriginalFilename());
        try {
            String url = uploadService.upload(file);
            return Result.success(url);
        } catch (Exception e) {
            log.error(e.toString());
            return Result.error("上传失败");
        }
    }
}
