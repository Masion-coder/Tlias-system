package com.tlias.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlias.module.Result;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@RestController
public class UploadController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) {
        log.info("接收到的参数:{}, {}, {}", name, age, file);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString() + "." + suffix;
        try {
            String savePath = UPLOAD_FOLDER + "img/";
            Files.createDirectories(Paths.get(savePath));
            String pathname = savePath + filename;
            Path path = Paths.get(pathname);
            Files.createFile(path);
            log.info("文件保存路径:{}", path);
            file.transferTo(path);
        } catch (Exception e) {
            log.error(e.toString());
            return Result.error("上传失败");
        }
        return Result.success(filename);
    }
    
}
