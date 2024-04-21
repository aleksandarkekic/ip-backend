package com.example.ip_backend.models.requests;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mail;
    private String city;
    private String role="USER";
    private Boolean accountConfirmed=false;



}
