package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.utils.FileDownloadUtil;
import com.mitrran.msulearningapi.utils.FileUploadUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class FileUploadController {

    @PostMapping("uploadFile")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();

        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        map.put("fileCode", fileCode);
        map.put("fileSize", size);
        map.put("url", "/downloadFile/" + fileCode);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileCode){
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
