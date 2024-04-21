package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "photo")
public class PhotoEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id", nullable = false)
    private ProgramEntity program;

}
