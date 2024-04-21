package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Attribute;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.entities.AttributeEntity;
import com.example.ip_backend.models.entities.LocationEntity;
import com.example.ip_backend.repositories.AttributeEntityRepository;
import com.example.ip_backend.services.AttributeService;
import com.example.ip_backend.services.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ModuleDescriptor;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl extends CrudJpaService<AttributeEntity, Integer> implements AttributeService {
    private final ModelMapper modelMapper;

    private final AttributeEntityRepository repository;


    public AttributeServiceImpl(ModelMapper modelMapper, AttributeEntityRepository repository) {
        super(repository,modelMapper,AttributeEntity.class);
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public List<String> getAllDistinctNames() {
        return repository.findDistinctName();
    }

    @Override
    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }

    @Override
    public List<String> findAllByCategoryId(Integer categoryId) {
        List<String> attributeList=repository.findAllByCategoryId(categoryId);
        return attributeList;
    }

    @Override
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
