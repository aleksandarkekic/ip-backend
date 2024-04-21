package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.entities.LocationEntity;
import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.repositories.LocationEntityRepository;
import com.example.ip_backend.services.LocationService;
import com.example.ip_backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl extends CrudJpaService<LocationEntity, Integer> implements LocationService {
    private final ModelMapper modelMapper;

    private final LocationEntityRepository locationEntityRepository;

    public LocationServiceImpl(ModelMapper modelMapper, LocationEntityRepository locationEntityRepository) {
        super(locationEntityRepository, modelMapper,LocationEntity.class);
        this.modelMapper = modelMapper;
        this.locationEntityRepository = locationEntityRepository;
    }

    @Override
    public List<String> getAllDistinctNames() {
        return locationEntityRepository.findDistinctName();
    }

    @Override
    public Integer findIdByName(String name) {
        return locationEntityRepository.findIdByName(name);
    }

    @Override
    public Boolean existsByName(String name) {
        return locationEntityRepository.existsByName(name);
    }
}
