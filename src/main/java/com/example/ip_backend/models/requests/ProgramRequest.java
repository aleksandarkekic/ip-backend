package com.example.ip_backend.models.requests;

import com.example.ip_backend.models.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ProgramRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer difficulty;
    private Integer duration;
    private Boolean active=true;
    private Integer categoryId;
    private Integer attributeId;
    private Integer locationId;
    private Integer userId=0;
    private String coach;
    private String contact;
    private Boolean deleted=false;
    private Timestamp createdTime;

}
