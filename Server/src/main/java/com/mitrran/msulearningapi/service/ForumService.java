package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.ForumComment;
import com.mitrran.msulearningapi.model.ForumLike;
import com.mitrran.msulearningapi.model.ForumPost;
import com.mitrran.msulearningapi.model.User;
import com.mitrran.msulearningapi.model.dto.*;
import com.mitrran.msulearningapi.repository.ForumCommentRepository;
import com.mitrran.msulearningapi.repository.ForumLikeRepository;
import com.mitrran.msulearningapi.repository.ForumPostRepository;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForumService {
    @Autowired
    private ForumPostRepository forumPostRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private ForumLikeRepository forumLikeRepository;

    public Map<String, Object> addNewForum(ForumRequest request) {
        Map<String, Object> map = new HashMap<>();

        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isPresent()) {
            ForumPost forumPost = new ForumPost();
            forumPost.setTitle(request.getTitle());
            forumPost.setContent(request.getContent());
            forumPost.setTopic(request.getTopic());
            forumPost.setUser(user.get());
            forumPost.setPostedOn(new Date());
            forumPost.setModifiedOn(new Date());
            ForumPost f = forumPostRepository.save(forumPost);

            map.put("status", "success");
            map.put("data", f);
        } else {
            map.put("status", "failes");
            map.put("msg", "User Doesn't exist");
        }
        return map;
    }

    public Map<String, Object> getAllPosts(Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<ForumPost> posts = forumPostRepository.findAll();
        Collections.reverse(posts);
        map.put("data", posts.stream().map(f -> mapToForumPostDto(f, userId)).toList());
        return map;
    }

    public Map<String, Object> getAllForumFaq(Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<ForumPost> faqs = forumPostRepository.findByIsFaqTrue();
        Collections.reverse(faqs);
        map.put("data", faqs.stream().map(f -> mapToForumPostDto(f, userId)).toList());
        return map;
    }


    public void addNewComment(ForumCommentDto forumCommentDto) {
        Optional<ForumPost> forumPost = forumPostRepository.findById(forumCommentDto.getForumId());
        if (forumPost.isPresent()) {
            Optional<User> user = userRepository.findById(forumCommentDto.getUserId());
            if (user.isPresent()) {
                ForumComment forumComment = new ForumComment();
                forumComment.setCommentedOn(new Date());
                forumComment.setComment(forumCommentDto.getComment());
                forumComment.setUser(user.get());
                forumComment.setForumPost(forumPost.get());

                forumCommentRepository.save(forumComment);

                forumPost.get().setModifiedOn(new Date());

                forumPostRepository.save(forumPost.get());
            } else {
                System.out.println("User not present");
            }
        } else {
            System.out.println("Forum not present");
        }
    }

    public void addOrUpdateLike(ForumLikeDto forumLikeDto) {
        Optional<ForumPost> forumPost = forumPostRepository.findById(forumLikeDto.getForumId());
        if (forumPost.isPresent()) {
            Optional<User> user = userRepository.findById(forumLikeDto.getUserId());
            if (user.isPresent()) {

                ForumLike forumLikeObj;

                Optional<ForumLike> forumLike = forumLikeRepository.findByUserIdAndForumId(forumLikeDto.getUserId(), forumLikeDto.getForumId());
                forumLikeObj = forumLike.orElseGet(ForumLike::new);
                forumLikeObj.setForumId(forumLikeDto.getForumId());
                forumLikeObj.setLiked(forumLikeDto.getIsLiked());
                forumLikeObj.setUserId(forumLikeDto.getUserId());
//                System.out.println(forumLikeDto.isLiked());
//                System.out.println(forumLikeObj.isLiked());
                forumLikeRepository.save(forumLikeObj);
            } else {
                System.out.println("User not present");
            }
        } else {
            System.out.println("Forum not present");
        }
    }

    public UserDto mapUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setDepartment(user.getDepartment());
        userDto.setProfileImage(user.getProfileImage());
        return userDto;
    }

    public ForumPostDto mapToForumPostDto(ForumPost forumPost, Long userId) {
        ForumPostDto forumPostDto = new ForumPostDto();

        forumPostDto.setId(forumPost.getId());
        forumPostDto.setForumComments(forumCommentRepository.findByForumPost_Id(forumPost.getId()));
        forumPostDto.setLikes(forumLikeRepository.countByForumId(forumPost.getId()));
        forumPostDto.setPostedOn(forumPost.getPostedOn());
        forumPostDto.setFaq(forumPost.isFaq());
        forumPostDto.setContent(forumPost.getContent());
        forumPostDto.setUser(forumPost.getUser());
        forumPostDto.setModifiedOn(forumPost.getModifiedOn());
        forumPostDto.setTopic(forumPost.getTopic());
        forumPostDto.setTitle(forumPost.getTitle());

        Optional<ForumLike> forumLike = forumLikeRepository.findByUserIdAndForumId(userId, forumPost.getId());
        if (forumLike.isPresent()) {
            forumPostDto.setUserLiked(forumLike.get().isLiked());
        } else {
            forumPostDto.setUserLiked(false);
        }


        return forumPostDto;
    }

    public void updateForumPost(ForumPostDto forumPostDto) {
        Optional<ForumPost> forumPost = forumPostRepository.findById(forumPostDto.getId());
        System.out.println(forumPostDto.isFaq());
        if (forumPost.isPresent()) {
            ForumPost forumPost1 = forumPost.get();
            forumPost1.setFaq(forumPostDto.isFaq());
            forumPost1.setModifiedOn(new Date());
            forumPostRepository.save(forumPost1);
        }
    }

    public String getAllForumsContents() {
        StringBuilder forums = new StringBuilder();
        List<ForumPost> forumPost = forumPostRepository.findAll();
        for (ForumPost f : forumPost) {
            forums.append(f.getContent());
        }
        return forums.toString();
    }
}