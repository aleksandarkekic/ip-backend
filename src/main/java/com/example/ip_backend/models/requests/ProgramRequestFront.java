package com.example.ip_backend.models.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProgramRequestFront {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer difficulty;
    private Integer duration;
    private Boolean active=true;
    private String categoryName;
    private String attributeName;
    private String locationName;
    private Integer userId=0;
    private String coach;
    private String contact;
}
