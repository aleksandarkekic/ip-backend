package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query("SELECT DISTINCT a.name FROM CategoryEntity a WHERE a.deleted = false")
    List<String> findDistinctName();
    @Query("SELECT  a.id FROM CategoryEntity a WHERE a.name=:name")

    Integer findIdByName(String name);

    Boolean existsByName(String name);
}
