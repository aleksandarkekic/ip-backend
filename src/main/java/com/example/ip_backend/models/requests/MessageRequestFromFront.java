package com.example.ip_backend.models.requests;

import lombok.Data;

@Data
public class MessageRequestFromFront {
    private Integer receiverId;
    private String text;
}
