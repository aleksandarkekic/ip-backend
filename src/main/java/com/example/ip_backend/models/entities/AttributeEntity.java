package com.example.ip_backend.models.entities;

import com.example.ip_backend.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Data
@Entity
@Table(name = "attribute")
public class AttributeEntity implements BaseEntity<Integer>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "deleted")
    private Boolean deleted;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
    @OneToMany(mappedBy = "attribute")
    @JsonIgnore
    private List<ProgramEntity> programs;



}
