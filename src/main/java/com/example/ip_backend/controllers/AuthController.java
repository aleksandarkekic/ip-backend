package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.AuthResponse;
import com.example.ip_backend.models.dto.User;
import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.models.requests.ConfirmEmail;
import com.example.ip_backend.models.requests.LoginRequest;
import com.example.ip_backend.models.requests.RegisterRequest;
import com.example.ip_backend.repositories.UserEntityRepository;
import com.example.ip_backend.security.JwtGenerator;
import com.example.ip_backend.services.EmailService;
import com.example.ip_backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final UserEntityRepository repository;
    private String email = "";
    private String pin = "";
    private String username ="";

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator,
                          ModelMapper modelMapper, EmailService emailService, UserEntityRepository repository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws NotFoundException {
        if(repository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        email=request.getMail();
        username= request.getUsername();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.insert(request, UserEntity.class);
        pin = String.format("%06d", new Random().nextInt(1000000));
        emailService.sendEmail(request.getMail(), "Fitness centar - confirm email", pin+" is your Fitness verification code");
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        System.out.println(token);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @GetMapping("/send-pin/{username}")
    public void sendPin(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        this.username = user.getUsername();
        email = user.getMail();
        pin = String.format("%06d", new Random().nextInt(1000000));
        emailService.sendEmail(email, "Fitness centar - confirm email", pin+" is your Fitness verification code");
    }

    @PostMapping("/confirm-pin")
    public Boolean  confirmEmail(@RequestBody ConfirmEmail request)  throws NotFoundException {
        if (!request.getPin().equals(pin))
            return false;
        User user = userService.getUserByUsername(this.username);
        user.setAccountConfirmed(true);
        userService.update(user.getId(), user, User.class);
        return true;
    }
    /*
    @GetMapping("/email-confirmed/{username}")
    public Boolean getEmailConfirmed(@PathVariable String username){
        return userService.getEmailConfirmed(username);
    }
    */



}

