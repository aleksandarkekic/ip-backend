package com.example.ip_backend.models.requests;

import lombok.Data;

@Data
public class ParticipateRequest {
    private Integer programId;
    private Integer userId;
}
