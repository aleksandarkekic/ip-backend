package com.example.ip_backend.models.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiaryRequest {
    private String exerciseName;
    private Integer duration;
    private Timestamp createdTime;
    private Integer weight;
    private Integer result;
    private Integer userId;
}
