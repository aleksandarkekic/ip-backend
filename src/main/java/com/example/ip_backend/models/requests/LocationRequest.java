package com.example.ip_backend.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LocationRequest {
    private String name;
}
