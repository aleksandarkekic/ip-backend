package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.Comment;
import com.example.ip_backend.models.entities.CommentEntity;

import java.util.List;

public interface CommentService extends CrudService<Integer> {
    List<Comment> getAllByProgramId(Integer id);
    Integer numOfElements(Integer programId);

}
