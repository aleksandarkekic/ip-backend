package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Attribute;
import com.example.ip_backend.services.AttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/attributes")
public class AttributeController {
    private final AttributeService service;

    public AttributeController(AttributeService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Attribute> findALl() throws NotFoundException {
        return service.findAll(Attribute.class);
    }

    @GetMapping("/distinct-names")
    public List<String> getAllDistinctNames(){
        return service.getAllDistinctNames();
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public List<String> findAllByCategoryId(@PathVariable Integer categoryId) {
        return service.findAllByCategoryId(categoryId);
    }

}
