package com.example.ip_backend.models.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String mail;
    private String city;
    private String role;
    private Boolean accountConfirmed=true;


}
