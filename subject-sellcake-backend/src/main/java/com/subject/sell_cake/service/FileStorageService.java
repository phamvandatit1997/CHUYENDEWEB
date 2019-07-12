package com.subject.sell_cake.service;

import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.FileStorageProperties;
import com.subject.sell_cake.response.APIStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String storaFile(MultipartFile file, String fileName) {

        try {
            if (!fileName.contains("..")) {
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }
            return this.fileStorageLocation.toString() + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(APIStatus.ERR_CANT_STORE);
        }
    }
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            }

            throw new ApplicationException(APIStatus.ERR_FILE_NOT_FOUND);

        } catch (MalformedURLException ex) {
            throw new ApplicationException(APIStatus.ERR_FILE_NOT_FOUND);
        }
    }
}
