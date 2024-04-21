package com.example.ip_backend.models.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String city;
    private String role;
    private String categoryName;
}
