package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.Attribute;

import java.util.List;

public interface AttributeService extends CrudService<Integer> {
    List<String> getAllDistinctNames();
    Integer findIdByName(String name);
    Boolean existsByName(String name);
    List<String> findAllByCategoryId(Integer id);

}
