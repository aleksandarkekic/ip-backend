package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.dto.User;
import com.example.ip_backend.models.dto.UserResponse;
import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.repositories.UserEntityRepository;
import com.example.ip_backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends CrudJpaService<UserEntity, Integer> implements UserService {
    private final UserEntityRepository repository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserEntityRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper, UserEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getUserByUsername(String username) {
        return modelMapper.map(repository.findByUsername(username), User.class);
    }
    @Override
    public Integer getCurrentId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findIdByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return super.getModelMapper().map(repository.findByUsername(username), User.class);
    }

    @Override
    public UserResponse getCurrentUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return modelMapper.map(repository.findByUsername(username), UserResponse.class);
    }

    @Override
    public String getCurrentRole() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findRoleByUsername(username);

    }

    @Override
    public Boolean isEmailConfirmed(String username) {
        return repository.isEmailConfirmed(username);
    }

    @Override
    public String findUsernameById(Integer id) {
        return repository.findUsernameById(id);
    }

    @Override
    public Boolean isSubscribed(Integer userId) {
        return repository.isSubscribed(userId);
    }

    @Override
    public Integer findCategoryIdByUserId(Integer userId) {
        return repository.findCategoryIdByUserId(userId);
    }

    @Override
    public Integer findIdByUsername(String username) {
        return repository.findIdByUsername(username);
    }

    @Override
    public List<User> findAllUsersWithRoleUser() {
        return repository.findAllUsersWithRoleUser().stream().map(m->(modelMapper.map(m, User.class))).collect(Collectors.toList());
    }

    @Override
    public Boolean ifRoleIsAdvisor(Integer id) {
        return repository.ifRoleIsAdvisor(id);
    }

    @Override
    public List<User> findAllUsersWithRoleAdvisor() {
        return repository.findAllUsersWithRoleAdvisor().stream().map(m->(modelMapper.map(m, User.class))).collect(Collectors.toList());
    }


}
