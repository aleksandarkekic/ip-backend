package com.example.ip_backend.models.requests;

import lombok.Data;

@Data
public class PhotoRequest {
    private String photoUrl;
    private Integer productId;
}