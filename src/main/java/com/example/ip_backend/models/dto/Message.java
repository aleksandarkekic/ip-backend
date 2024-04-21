package com.example.ip_backend.models.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Message {
    private Integer id;
    private String chatId;
    private Integer senderId;
    private Integer receiverId;
    private String text;
    private Timestamp createdTime;
    private Boolean advisor;
    private Boolean seen;

}
