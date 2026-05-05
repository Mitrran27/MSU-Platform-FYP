package com.mitrran.msulearningapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String filePath;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private Date uploadedOn;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;

}
