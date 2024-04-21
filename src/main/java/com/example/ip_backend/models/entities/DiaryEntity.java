package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "diary")
public class DiaryEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "exercise_name")
    private String exerciseName;
    @Basic
    @Column(name = "duration")
    private Integer duration;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "result")
    private Integer result;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;


}
