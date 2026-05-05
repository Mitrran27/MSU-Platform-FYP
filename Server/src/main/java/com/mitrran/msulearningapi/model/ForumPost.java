package com.mitrran.msulearningapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String topic;
    private String content;
    private boolean isFaq;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date postedOn;
    private Date modifiedOn;
    private int likes;
}
