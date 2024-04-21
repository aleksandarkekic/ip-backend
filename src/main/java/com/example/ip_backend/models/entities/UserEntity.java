package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "role")
    private String role;
    @Basic
    @Column(name = "account_confirmed")
    private Boolean accountConfirmed;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<DiaryEntity> diaries;
    @OneToMany(mappedBy = "senderId")
    @JsonIgnore
    private List<MessageEntity> messagesSender;
    @OneToMany(mappedBy = "receiverId")
    @JsonIgnore
    private List<MessageEntity> messagesReceiver;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;



}
