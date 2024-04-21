package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService extends CrudService<Integer> {
    Photo uploadPhoto(Integer productId, MultipartFile file) throws IOException;
    List<Photo> getAllByProgramId(Integer id);
}
