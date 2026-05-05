package com.mitrran.msulearningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnnouncementDto {

    private String title;
    private String description;
    private Long userId;
}
