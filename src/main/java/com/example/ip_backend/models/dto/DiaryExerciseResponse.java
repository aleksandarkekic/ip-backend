package com.example.ip_backend.models.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiaryExerciseResponse {
    private Integer id;
    private String exerciseName;
    private Integer duration;
    private Timestamp createdTime;
    private Integer result;
    private Integer userId;
}
