package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;

import java.util.List;

public interface ParticipateService extends CrudService<Integer> {
    List<Integer> findByUserId(Integer userId);
}
