package com.tlias.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public String upload(MultipartFile file) throws IOException;
}
