package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.UserEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Diary {
    private Integer id;
    private String exerciseName;
    private Integer duration;
    private Timestamp createdTime;
    private Integer weight;
    private Integer result;
    private Integer userId;
}
