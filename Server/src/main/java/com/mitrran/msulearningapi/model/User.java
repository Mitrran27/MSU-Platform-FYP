package com.mitrran.msulearningapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String profileImage;
    private String phoneNumber;
    private String department;
    @Column(name = "connected", nullable = false)
    private Boolean connected = false;

}
