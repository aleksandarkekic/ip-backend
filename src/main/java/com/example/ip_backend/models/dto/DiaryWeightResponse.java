package com.example.ip_backend.models.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiaryWeightResponse {
    private Integer id;
    private String exerciseName;
    private Integer weight;
    private Timestamp createdTime;
    private Integer userId;
}
