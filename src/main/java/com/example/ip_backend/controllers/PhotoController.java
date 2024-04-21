package com.example.ip_backend.controllers;

import com.example.ip_backend.base.CrudController;
import com.example.ip_backend.models.dto.Photo;
import com.example.ip_backend.models.requests.PhotoRequest;
import com.example.ip_backend.services.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/photos")
public class PhotoController extends CrudController<Integer, PhotoRequest, Photo> {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        super(Photo.class, photoService);
        this.photoService = photoService;
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Integer id) throws IOException {
        Photo photo = photoService.uploadPhoto(id,file);
        if(photo.getPhotoUrl() != null){
            return ResponseEntity.ok(photo);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/program/{id}")
    public List<Photo> getPhotosByProgramId(@PathVariable Integer id){
        return photoService.getAllByProgramId(id);
    }

    @GetMapping("/program/{id}/first-photo")
    public Photo getFirstPhotoByProgramId(@PathVariable Integer id){
        List<Photo> photos = photoService.getAllByProgramId(id);
        if (!photos.isEmpty()) {
            return photos.get(0);
        } else {
            Photo noImage = new Photo();
            noImage.setPhotoUrl("https://i.ibb.co/VNvhtZJ/Screenshot-2023-07-29-220419.png");
            return noImage;
        }
    }
}
