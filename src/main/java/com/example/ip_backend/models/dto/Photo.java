package com.example.ip_backend.models.dto;

import lombok.Data;

@Data
public class Photo {
    private Integer id;
    private String photoUrl;
    private Integer programId;
}
