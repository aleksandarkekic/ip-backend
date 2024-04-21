package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Participate;
import com.example.ip_backend.models.requests.ParticipateRequest;
import com.example.ip_backend.models.requests.ParticipateRequestFront;
import com.example.ip_backend.services.ParticipateService;
import com.example.ip_backend.services.UserService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/participate")
public class ParticipateController {
    private final ParticipateService participateService;

    private final UserService userService;
    public ParticipateController(ParticipateService participateService, UserService userService) {
        this.participateService = participateService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
        public List<Integer> findByUserId(@PathVariable Integer userId){
        return participateService.findByUserId(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Participate insert(@RequestBody ParticipateRequestFront request) throws NotFoundException {
        ParticipateRequest participateRequest=new ParticipateRequest();
        participateRequest.setProgramId(request.getProgramId());
        participateRequest.setUserId(userService.getCurrentId());
        return participateService.insert(participateRequest,Participate.class);
    }
}
