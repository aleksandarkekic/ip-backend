package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.ProgramEntity;
import com.example.ip_backend.models.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class Comment {
    private Integer id;
    private Timestamp createdTime;
    private String text;
    private Boolean deleted;
    private Integer programId;
    private Integer userId;
}
