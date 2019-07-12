package com.subject.sell_cake.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    // upload file
    public String uploadFile(MultipartFile file, String fileName);
}
