package com.mitrran.msulearningapi.repository;

import com.mitrran.msulearningapi.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
