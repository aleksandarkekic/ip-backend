package com.example.ip_backend.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Location {
    private Integer id;
    private String name;
}
