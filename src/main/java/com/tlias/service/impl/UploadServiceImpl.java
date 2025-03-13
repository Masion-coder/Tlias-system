package com.tlias.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tlias.service.UploadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @Value("${prop.download-folder}")
    private String DOWNLOAD_FOLDER;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString() + "." + suffix;
        String savePath = UPLOAD_FOLDER + "img/";
        Files.createDirectories(Paths.get(savePath));
        String pathname = savePath + filename;
        Path path = Paths.get(pathname);
        Files.createFile(path);
        log.info("文件保存路径:{}", path);
        file.transferTo(path);
        return DOWNLOAD_FOLDER + "img/" + filename;
    }
}
