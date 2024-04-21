package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Diary;
import com.example.ip_backend.models.dto.DiaryExerciseResponse;
import com.example.ip_backend.models.dto.DiaryWeightResponse;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.requests.DiaryRequest;
import com.example.ip_backend.services.DiaryService;
import com.example.ip_backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/diaries")
public class DiaryController {
    private final DiaryService diaryService;
    private final UserService userService;

    private ModelMapper modelMapper;

    public DiaryController(DiaryService diaryService, UserService userService, ModelMapper modelMapper) {
        this.diaryService = diaryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diary insert(@RequestBody DiaryRequest diaryRequest) throws NotFoundException {
        diaryRequest.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        diaryRequest.setUserId(userService.getCurrentId());
        return diaryService.insert(diaryRequest, Diary.class);

    }

    @GetMapping("/weightDiary")
    public List<DiaryWeightResponse> getWeightsDiary() throws NotFoundException {
        List<Diary> allDiaries=diaryService.findAllByUserId(userService.getCurrentId());
        List<DiaryWeightResponse> diaryWeight=new ArrayList<>();;
        for(Diary diary:allDiaries){
            if(diary.getWeight()!=null) {
                DiaryWeightResponse diaryWeightResponse = new DiaryWeightResponse();
                diaryWeightResponse.setId(diary.getId());
                diaryWeightResponse.setCreatedTime(diary.getCreatedTime());
                diaryWeightResponse.setWeight(diary.getWeight());
                diaryWeightResponse.setUserId(diary.getUserId());
                diaryWeightResponse.setExerciseName(diary.getExerciseName());
                diaryWeight.add(diaryWeightResponse);
            }
        }
        return diaryWeight;
    }

    @GetMapping("/exerciseDiaries")
    public List<DiaryExerciseResponse> getExerciseDiaries() throws NotFoundException {
        List<Diary> allDiaries=diaryService.findAllByUserId(userService.getCurrentId());
        List<DiaryExerciseResponse> diaryExercise=new ArrayList<>();;
        for(Diary diary:allDiaries){
            if(diary.getWeight()==null) {
                DiaryExerciseResponse diaryExerciseResponse = new DiaryExerciseResponse();
                diaryExerciseResponse.setId(diary.getId());
                diaryExerciseResponse.setCreatedTime(diary.getCreatedTime());
                diaryExerciseResponse.setUserId(diary.getUserId());
                diaryExerciseResponse.setExerciseName(diary.getExerciseName());
                diaryExerciseResponse.setDuration(diary.getDuration());
                diaryExerciseResponse.setResult(diary.getResult());

                diaryExercise.add(diaryExerciseResponse);

            }
        }
        return diaryExercise;
    }
}
