package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.dto.ForumCommentDto;
import com.mitrran.msulearningapi.model.dto.ForumLikeDto;
import com.mitrran.msulearningapi.model.dto.ForumPostDto;
import com.mitrran.msulearningapi.model.dto.ForumRequest;
import com.mitrran.msulearningapi.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/forum")
public class ForumPostController {

    @Autowired
    private ForumService forumService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addNewForum(@RequestBody ForumRequest post) {
        return new ResponseEntity<>(forumService.addNewForum(post), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAllPosts(@PathVariable Long id) {
        return new ResponseEntity<>(forumService.getAllPosts(id), HttpStatus.OK);
    }

    @GetMapping("faq/{id}")
    public ResponseEntity<Map<String, Object>> getAllForumFaq(@PathVariable Long id) {
        return new ResponseEntity<>(forumService.getAllForumFaq(id), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> addNewComment(@RequestBody ForumCommentDto forumCommentDto) {
        forumService.addNewComment(forumCommentDto);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("msg", "Comment added successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> addOrUpdateLike(@RequestBody ForumLikeDto forumLikeDto) {
        forumService.addOrUpdateLike(forumLikeDto);
        System.out.println(forumLikeDto);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("msg", "Like added or updated successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> updateForum(@RequestBody ForumPostDto forumPostDto) {
        Map<String, Object> map = new HashMap<>();
        forumService.updateForumPost(forumPostDto);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
