package com.mitrran.msulearningapi.repository;

import com.mitrran.msulearningapi.model.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
    @Query("select f from ForumComment f where f.forumPost.id = ?1")
    List<ForumComment> findByForumPost_Id(Long id);

}
