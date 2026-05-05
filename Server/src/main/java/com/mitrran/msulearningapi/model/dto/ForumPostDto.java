package com.mitrran.msulearningapi.model.dto;

import com.mitrran.msulearningapi.model.ForumComment;
import com.mitrran.msulearningapi.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForumPostDto {

    private Long id;
    private String title;
    private String topic;
    private String content;
    private boolean faq;
    private User user;
    private Date postedOn;
    private Date modifiedOn;
    private int likes;
    private List<ForumComment> forumComments;
    private boolean isUserLiked;
}
