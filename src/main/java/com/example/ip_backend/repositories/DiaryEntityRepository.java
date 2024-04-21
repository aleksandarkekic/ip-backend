package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.CommentEntity;
import com.example.ip_backend.models.entities.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryEntityRepository extends JpaRepository<DiaryEntity, Integer> {
    @Query("SELECT d FROM DiaryEntity d WHERE d.user.id=:userId")
    List<DiaryEntity> findAllByUserId(Integer userId);
}
