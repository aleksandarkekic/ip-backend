package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Diary;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.entities.DiaryEntity;
import com.example.ip_backend.models.entities.PhotoEntity;
import com.example.ip_backend.repositories.DiaryEntityRepository;
import com.example.ip_backend.repositories.PhotoEntityRepository;
import com.example.ip_backend.services.DiaryService;
import com.example.ip_backend.services.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl extends CrudJpaService<DiaryEntity, Integer> implements DiaryService {
    private final DiaryEntityRepository repository;
    private final ModelMapper mapper;

    public DiaryServiceImpl(DiaryEntityRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, DiaryEntity.class);
        this.repository = repository;
        this.mapper = modelMapper;
    }

    @Override
    public List<Diary> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId).stream().map(m->(mapper.map(m, Diary.class))).collect(Collectors.toList());
    }
}
