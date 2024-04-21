package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.CategoryEntity;
import com.example.ip_backend.models.entities.ParticipateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipateEntityRepository extends JpaRepository<ParticipateEntity, Integer> {
    @Query("SELECT p.programId FROM ParticipateEntity p WHERE p.userId=:userId")
    List<Integer> findByUserId(Integer userId);
}
