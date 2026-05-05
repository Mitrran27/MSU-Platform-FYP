package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.Announcement;
import com.mitrran.msulearningapi.model.User;
import com.mitrran.msulearningapi.model.dto.AnnouncementDto;
import com.mitrran.msulearningapi.repository.AnnouncementRepository;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserRepository userRepository;


    public Map<String, Object> getAllAnnouncements() {
        Map<String, Object> map = new HashMap<>();

        List<Announcement> announcements = announcementRepository.findAll();
        Collections.reverse(announcements);
        map.put("data", announcements);
        return map;
    }

    public Map<String, Object> addNewAnnouncement(AnnouncementDto announcementDto) {
        Map<String, Object> map = new HashMap<>();
        Optional<User> user = userRepository.findById(announcementDto.getUserId());

        if (user.isPresent()) {
            Announcement announcement = new Announcement();

            announcement.setTitle(announcementDto.getTitle());
            announcement.setDescription(announcementDto.getDescription());
            announcement.setCreatedBy(user.get());
            announcement.setModifiedOn(new Date());
            announcement.setCreatedOn(new Date());

            map.put("status", "success");
            map.put("data", announcementRepository.save(announcement));
        } else {
            map.put("status", "failed");
            map.put("msg", MessageFormat.format("User with {0} doesn't exist", announcementDto.getUserId()));
        }

        return map;
    }
}
