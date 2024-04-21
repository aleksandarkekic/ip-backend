package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.LocationEntity;
import com.example.ip_backend.models.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LocationEntityRepository extends JpaRepository<LocationEntity, Integer> {
    @Query("SELECT DISTINCT a.name FROM LocationEntity a")

    List<String> findDistinctName();
    @Query("SELECT  a.id FROM LocationEntity a WHERE a.name=:name")
    Integer findIdByName(String name);

    Boolean existsByName(String name);

}
