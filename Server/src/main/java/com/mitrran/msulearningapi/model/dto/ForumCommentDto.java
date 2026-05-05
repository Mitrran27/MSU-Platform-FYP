package com.mitrran.msulearningapi.model.dto;

import com.mitrran.msulearningapi.model.ForumPost;
import com.mitrran.msulearningapi.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForumCommentDto {
    private String comment;
    private Long userId;
    private Long forumId;
}
