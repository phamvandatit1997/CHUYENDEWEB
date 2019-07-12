package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.FileStorageService;
import com.subject.sell_cake.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(path = Constant.FILE)
public class FileController extends AbstractBasedAPI {

    @Autowired
    FileStorageService fileStorageService;

    @RequestMapping(path = Constant.WITHIN_ID, method = RequestMethod.GET)
    public ResponseEntity<Resource> dowloadFile(@PathVariable("id") String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new ApplicationException(APIStatus.ERR_FILE_TYPE);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + resource.getFilename() + "\"")
                .body(resource);
    }
}
