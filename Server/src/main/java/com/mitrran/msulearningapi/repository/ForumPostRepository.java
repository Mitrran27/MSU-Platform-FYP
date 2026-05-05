package com.mitrran.msulearningapi.repository;

import com.mitrran.msulearningapi.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
    List<ForumPost> findByIsFaqTrue();

}
