package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
  List<CommentEntity> getAllByProgramId(Integer id);

  @Query("select count(*) from CommentEntity u where u.program.id=:programId")
  Integer numOfElements(Integer programId);

  Integer countByProgramId(Integer programId);


}
