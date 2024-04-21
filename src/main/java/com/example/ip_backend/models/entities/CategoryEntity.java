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
@Table(name = "category")
public class CategoryEntity implements BaseEntity<Integer> {
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
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<AttributeEntity> attributes;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ProgramEntity> programs;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<UserEntity> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
