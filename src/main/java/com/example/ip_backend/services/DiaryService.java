package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.Diary;
import com.example.ip_backend.models.entities.DiaryEntity;

import java.util.List;

public interface DiaryService extends CrudService<Integer> {
    List<Diary> findAllByUserId(Integer userId);
}
