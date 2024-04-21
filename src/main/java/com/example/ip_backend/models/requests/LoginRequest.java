package com.example.ip_backend.models.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
