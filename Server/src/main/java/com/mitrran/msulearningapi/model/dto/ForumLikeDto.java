package com.mitrran.msulearningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForumLikeDto {
    private Long userId;
    private Long forumId;
    private Boolean isLiked;

    @Override
    public String toString() {
        return "ForumLikeDto{" +
                "userId=" + userId +
                ", forumId=" + forumId +
                ", isLiked=" + isLiked +
                '}';
    }
}
