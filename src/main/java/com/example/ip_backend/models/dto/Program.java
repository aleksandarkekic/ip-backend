package com.example.ip_backend.models.dto;

import com.example.ip_backend.models.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Program {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer difficulty;
    private Integer duration;
    private Boolean active;
    private String categoryName;
    private String attributeName;
    private String locationName;
    private String userUsername;
    private String coach;
    private String contact;
    private Boolean deleted;
    private Timestamp createdTime;

}
