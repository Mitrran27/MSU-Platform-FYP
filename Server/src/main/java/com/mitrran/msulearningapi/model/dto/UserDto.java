package com.mitrran.msulearningapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String profileImage;
    private String phoneNumber;
    private String department;
    private Boolean connected = false;

}
