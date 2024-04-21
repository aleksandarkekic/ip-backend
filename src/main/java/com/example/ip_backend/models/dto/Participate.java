package com.example.ip_backend.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Participate {
    private Integer id;
    private Integer programId;
    private Integer userId;

}
