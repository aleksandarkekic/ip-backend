package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.entities.CategoryEntity;
import com.example.ip_backend.repositories.CategoryEntityRepository;
import com.example.ip_backend.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CrudJpaService<CategoryEntity, Integer> implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryEntityRepository categoryEntityRepository;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryEntityRepository categoryEntityRepository) {
        super(categoryEntityRepository,modelMapper,CategoryEntity.class);
        this.modelMapper = modelMapper;
        this.categoryEntityRepository = categoryEntityRepository;
    }

    @Override
    public List<String> getAllDistinctNames() {
        return categoryEntityRepository.findDistinctName();
    }

    @Override
    public Integer findIdByName(String name) {
        return categoryEntityRepository.findIdByName(name);
    }
    @Override
    public Boolean existsByName(String name) {
        return categoryEntityRepository.existsByName(name);
    }
}
