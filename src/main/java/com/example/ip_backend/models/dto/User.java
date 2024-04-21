package com.example.ip_backend.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mail;
    private String city;
    private String role;
    private Boolean accountConfirmed;
}
