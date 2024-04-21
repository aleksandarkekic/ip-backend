package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.entities.ParticipateEntity;
import com.example.ip_backend.models.entities.PhotoEntity;
import com.example.ip_backend.repositories.ParticipateEntityRepository;
import com.example.ip_backend.repositories.PhotoEntityRepository;
import com.example.ip_backend.services.ParticipateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipateServiceImpl extends CrudJpaService<ParticipateEntity, Integer> implements ParticipateService {
    private final ParticipateEntityRepository repository;
    private final ModelMapper mapper;

    public ParticipateServiceImpl(ParticipateEntityRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, ParticipateEntity.class);
        this.repository = repository;
        this.mapper = modelMapper;
    }

    @Override
    public List<Integer> findByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }
}
