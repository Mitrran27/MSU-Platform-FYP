package com.mitrran.msulearningapi.model.dto;

import com.mitrran.msulearningapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceRequest {
    private String title;
    private String description;
    private Long userId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Long fileSize;
}
