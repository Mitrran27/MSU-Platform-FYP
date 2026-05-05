package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.Resources;
import com.mitrran.msulearningapi.model.User;
import com.mitrran.msulearningapi.model.dto.ResourceRequest;
import com.mitrran.msulearningapi.repository.ResourceRepository;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;


    public Map<String, Object> addNewResource(ResourceRequest resourceRequest) {
        Map<String, Object> map = new HashMap<>();

        Resources resources = new Resources();

        Optional<User> user = userRepository.findById(resourceRequest.getUserId());

        if (user.isPresent()) {
            resources.setFileName(resourceRequest.getFileName());
            resources.setTitle(resourceRequest.getTitle());
            resources.setDescription(resourceRequest.getDescription());
            resources.setFilePath(resourceRequest.getFilePath());
            resources.setFileSize(resourceRequest.getFileSize());
            resources.setUploadedBy(user.get());
            resources.setUploadedOn(new Date());
            Resources r = resourceRepository.save(resources);
            map.put("status", "success");
            map.put("data", r);
        } else {
            map.put("status", "failed");
            map.put("msg", "User not present");
        }
        return map;
    }

    public Map<String, Object> getAllResources() {
        Map<String, Object> map = new HashMap<>();

        map.put("status", "success");
        List<Resources> resources = resourceRepository.findAll();
        Collections.reverse(resources);
        map.put("data", resources);
        return map;
    }
}
