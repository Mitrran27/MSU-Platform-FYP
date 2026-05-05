package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.dto.AnnouncementDto;
import com.mitrran.msulearningapi.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
// @CrossOrigin("*")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllAnnouncements() {
        return new ResponseEntity<>(announcementService.getAllAnnouncements(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addNewAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        return new ResponseEntity<>(announcementService.addNewAnnouncement(announcementDto), HttpStatus.OK);
    }
}
