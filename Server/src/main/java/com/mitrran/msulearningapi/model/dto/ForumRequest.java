package com.mitrran.msulearningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForumRequest {
    private String topic;
    private String content;
    private String title;
    private Long userId;
}
