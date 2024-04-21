package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Comment;
import com.example.ip_backend.models.entities.CommentEntity;
import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.repositories.CommentEntityRepository;
import com.example.ip_backend.repositories.UserEntityRepository;
import com.example.ip_backend.services.CommentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends CrudJpaService<CommentEntity, Integer> implements CommentService {
    private final CommentEntityRepository repository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentEntityRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, CommentEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Comment> getAllByProgramId(Integer id) {
        return repository.getAllByProgramId(id).stream().map(m-> modelMapper.map(m,Comment.class)).collect(Collectors.toList());
    }

    @Override
    public Integer numOfElements(Integer programId) {
        return repository.numOfElements(programId);
    }


}
