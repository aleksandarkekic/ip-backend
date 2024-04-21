package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<Integer> {
    List<String> getAllDistinctNames();

    Integer findIdByName(String name);
    Boolean existsByName(String name);

}
