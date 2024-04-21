package com.example.ip_backend.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageRequest {
    private String chatId;
    private Integer senderId;
    private Integer receiverId;
    private String text;
    private Timestamp createdTime;
    private Boolean seen=false;
    private Boolean advisor=false;
}
