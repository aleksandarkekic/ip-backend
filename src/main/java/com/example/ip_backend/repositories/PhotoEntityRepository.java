package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.MessageEntity;
import com.example.ip_backend.models.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoEntityRepository extends JpaRepository<PhotoEntity, Integer> {
    @Override
    List<PhotoEntity> findAllById(Iterable<Integer> integers);
    List<PhotoEntity>findAllByProgramId(Integer id);
}
