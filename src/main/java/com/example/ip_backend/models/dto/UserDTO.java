package com.example.ip_backend.models.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mail;
    private String city;
    private String role;
    private Boolean accountConfirmed;
    private Integer categoryId;
}
