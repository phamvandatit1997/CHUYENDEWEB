package com.subject.sell_cake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        return fileStorageService.storaFile(file,fileName);
    }
}
