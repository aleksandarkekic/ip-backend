package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.ProgramEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class SingleLocation {
    private Integer id;
    private String name;
    private List<ProgramEntity> programs;
}
