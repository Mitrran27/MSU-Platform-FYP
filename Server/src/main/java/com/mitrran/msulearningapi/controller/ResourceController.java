package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.dto.ResourceRequest;
import com.mitrran.msulearningapi.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addResource(@RequestBody ResourceRequest resourceRequest) {
        return new ResponseEntity<>(resourceService.addNewResource(resourceRequest), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllResources() {
        return new ResponseEntity<>(resourceService.getAllResources(), HttpStatus.OK);
    }
}
