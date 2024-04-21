package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.models.dto.Photo;
import com.example.ip_backend.models.dto.PhotoUploadResponse;
import com.example.ip_backend.models.entities.CommentEntity;
import com.example.ip_backend.models.entities.PhotoEntity;
import com.example.ip_backend.repositories.PhotoEntityRepository;
import com.example.ip_backend.services.CommentService;
import com.example.ip_backend.services.PhotoService;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl extends CrudJpaService<PhotoEntity, Integer> implements PhotoService {
    public final String IMGBB_API_KEY="d205a2d7c2440b28437558f067dfa480";
    private final PhotoEntityRepository repository;
    private final ModelMapper mapper;

    public PhotoServiceImpl(PhotoEntityRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, PhotoEntity.class);
        this.repository = repository;
        this.mapper = modelMapper;
    }
    @Override
    public Photo uploadPhoto(Integer programId, MultipartFile file) throws IOException {
        String imgbbApiKey = IMGBB_API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        byte[] imageBytes = IOUtils.toByteArray(file.getInputStream());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);
        body.add("key",imgbbApiKey);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<PhotoUploadResponse> responseEntity = restTemplate.postForEntity(
                "https://api.imgbb.com/1/upload",
                requestEntity,
                PhotoUploadResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            PhotoUploadResponse response = responseEntity.getBody();
            if (response != null && response.isSuccess()) {
                String photoUrl = response.getData().getUrl();
                Photo photo = new Photo();
                photo.setPhotoUrl(photoUrl);
                photo.setProgramId(programId);
                return super.insert(photo,Photo.class);
            } else {
                // Handle the case where the response is null or the upload was not successful
                return null;
            }
        } else {
            // Handle the case where the API request was not successful
            return null;
        }
    }

    @Override
    public List<Photo> getAllByProgramId(Integer id) {
        return repository.findAllByProgramId(id)
                .stream().map(m->mapper.map(m,Photo.class))
                .collect(Collectors.toList());
    }
}
