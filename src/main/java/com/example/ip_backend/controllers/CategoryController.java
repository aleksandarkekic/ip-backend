package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Category;
import com.example.ip_backend.models.dto.Location;
import com.example.ip_backend.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<Category> findAll() throws NotFoundException {
        return categoryService.findAll(Category.class);
    }
    @GetMapping("/distinct-names")
    public List<String> getAllDistinctNames(){
        return categoryService.getAllDistinctNames();
    }

    @GetMapping("/{name}")
    public Integer findIdByName(@PathVariable String name){
        return categoryService.findIdByName(name);
    }
}
