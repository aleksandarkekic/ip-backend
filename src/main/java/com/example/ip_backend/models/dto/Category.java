package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.AttributeEntity;
import com.example.ip_backend.models.entities.ProgramEntity;
import com.example.ip_backend.models.entities.UserEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Integer id;
    private String name;

}
