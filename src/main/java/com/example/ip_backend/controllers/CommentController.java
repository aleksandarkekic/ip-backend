package com.example.ip_backend.controllers;

import com.example.ip_backend.base.CrudController;
import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.AuthResponse;
import com.example.ip_backend.models.dto.Comment;
import com.example.ip_backend.models.requests.CommentRequest;
import com.example.ip_backend.security.SecurityConsts;
import com.example.ip_backend.services.CommentService;
import com.example.ip_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/programs/{id}")
    public List<Comment> getAllByProgramId(@PathVariable Integer id){
        return commentService.getAllByProgramId(id);
    }

    @PostMapping("/programs/{id}")
    public ResponseEntity<Comment> insert(@RequestBody CommentRequest request, @PathVariable Integer id) throws NotFoundException {
        request.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        request.setProgramId(id);
        request.setUserId(userService.getCurrentId());
        commentService.insert(request, Comment.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws NotFoundException {
        Comment comment=commentService.findById(id,Comment.class);
        if(Objects.equals(comment.getUserId(),userService.getCurrentId()) || userService.getCurrentUser().getRole().equals(SecurityConsts.ADMIN))
            commentService.delete(id);
    }

    @GetMapping("/numOfElements/{programId}")
    public Integer numOfElements(@PathVariable Integer programId){
       return commentService.numOfElements(programId);
    }
}
