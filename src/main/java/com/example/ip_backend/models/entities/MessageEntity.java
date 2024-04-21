package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Data
@Entity
@Table(name = "message")
public class MessageEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "sender_id")
    private Integer senderId;
    @Basic
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Basic
    @Column(name = "seen")
    private Boolean seen;
    @Basic
    @Column(name = "advisor")
    private Boolean advisor;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Basic
    @Column(name = "chat_id")
    private String chatId;
    /*
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    private UserEntity receiver;


     */

}
