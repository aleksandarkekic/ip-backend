package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.CategoryEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Attribute {
    private Integer id;
    private String name;
    private Integer categoryId;
}
