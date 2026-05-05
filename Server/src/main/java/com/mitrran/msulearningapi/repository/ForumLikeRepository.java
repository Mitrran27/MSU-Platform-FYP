package com.mitrran.msulearningapi.repository;

import com.mitrran.msulearningapi.model.ForumLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForumLikeRepository extends JpaRepository<ForumLike, Long> {
    @Query("select f from ForumLike f where f.userId = ?1 and f.forumId = ?2")
    Optional<ForumLike> findByUserIdAndForumId(Long userId, Long forumId);

    @Query("select count(f) from ForumLike f where f.forumId = ?1 and f.isLiked = true ")
    int countByForumId(Long forumId);




}
