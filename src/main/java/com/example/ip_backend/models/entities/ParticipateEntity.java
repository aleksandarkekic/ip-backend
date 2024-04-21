package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "participate")
public class ParticipateEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "program_id")
    private Integer programId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;


}
