package com.example.ip_backend.models.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentRequest {
    private Timestamp createdTime;
    private String text;
    private Boolean deleted;
    private Integer programId;
    private Integer userId;
}
