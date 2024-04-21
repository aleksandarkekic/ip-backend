package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.AttributeEntity;
import com.example.ip_backend.models.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttributeEntityRepository extends JpaRepository<AttributeEntity, Integer> {
    @Query("SELECT DISTINCT a.name FROM AttributeEntity a WHERE a.deleted = false" )

    List<String> findDistinctName();
    @Query("SELECT  a.id FROM AttributeEntity a WHERE a.name=:name AND a.deleted = false ")
    Integer findIdByName(String name);
    Boolean existsByName(String name);

    @Query("SELECT a.name FROM AttributeEntity a WHERE a.category.id=:id AND a.deleted = false")
    List<String> findAllByCategoryId(Integer id);




}
