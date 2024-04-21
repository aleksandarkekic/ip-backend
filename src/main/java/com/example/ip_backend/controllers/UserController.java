package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.dto.UpdateUserDTO;
import com.example.ip_backend.models.dto.User;
import com.example.ip_backend.models.dto.UserResponse;
import com.example.ip_backend.models.requests.SubscribeToCategoryRequest;
import com.example.ip_backend.security.SecurityConsts;
import com.example.ip_backend.services.CategoryService;
import com.example.ip_backend.services.EmailService;
import com.example.ip_backend.services.ProgramService;
import com.example.ip_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private CategoryService categoryService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, CategoryService categoryService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> findAll() throws NotFoundException {return userService.findAll(User.class);}

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) throws NotFoundException { return userService.findById(id,User.class);}

    @GetMapping("/current-role")
    public String getCurrentRole(){
        return userService.getCurrentRole();
    }

    @GetMapping("/email-confirmed/{username}")
    public Boolean isEmailConfirmed(@PathVariable String username){
        return userService.isEmailConfirmed(username);
    }

    @GetMapping("/current-id")
    public Integer getCurrentId(){
        return userService.getCurrentId();
    }

    @GetMapping("/current-user-info")
    public UserResponse getCurrentUserInfo(){
        return userService.getCurrentUserInfo();
    }

    @GetMapping("/username/{id}")
    public String findUsernameById(@PathVariable Integer id){
        return userService.findUsernameById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody UpdateUserDTO updateUserDTO) throws NotFoundException {
        User user=userService.findById(id,User.class);
        updateUserDTO.setUsername(user.getUsername());
        updateUserDTO.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        updateUserDTO.setRole(user.getRole());

        if (Objects.equals(user.getId(), userService.getCurrentId()) || userService.getCurrentUser().getRole().equals(SecurityConsts.ADMIN)) {
             userService.update(id,updateUserDTO,User.class);
            return new ResponseEntity<>("successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not successfully!", HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/categorySub/{id}")
    public ResponseEntity<String> updateAddCategorySub(@PathVariable Integer id, @RequestParam String categoryName) throws NotFoundException {
        User user=userService.findById(id,User.class);
        SubscribeToCategoryRequest updateUserDTO=new SubscribeToCategoryRequest();
        updateUserDTO.setUsername(user.getUsername());
        updateUserDTO.setPassword(user.getPassword());
        updateUserDTO.setRole(user.getRole());
        updateUserDTO.setFirstName(user.getFirstName());
        updateUserDTO.setLastName(user.getLastName());
        updateUserDTO.setMail(user.getMail());
        updateUserDTO.setCity(user.getCity());
        Integer idValue=categoryService.findIdByName(categoryName);
        updateUserDTO.setCategoryId(idValue);

        if (Objects.equals(user.getId(), userService.getCurrentId()) || userService.getCurrentUser().getRole().equals(SecurityConsts.ADMIN)) {
            userService.update(id,updateUserDTO,User.class);
            return new ResponseEntity<>("successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not successfully!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/user")
    public List<User> findAllUsersWithRoleUser() {
        return userService.findAllUsersWithRoleUser();
    }

    @GetMapping("/advisor")
    public List<User> findAllUsersWithRoleAdvisor() {
        return userService.findAllUsersWithRoleAdvisor();
    }
    @GetMapping("/isSubscribed")
    public Boolean isSubscribed(@RequestParam Integer userId) {
        return userService.isSubscribed(userId);
    }
    @GetMapping("/categoryId")
    public Integer findCategoryIdByUserId(@RequestParam Integer userId) {
        return userService.findCategoryIdByUserId(userId);
    }
    @Autowired
    private ProgramService programService;


    @Autowired
    private EmailService emailService;

    @GetMapping("/example")
    public void sendDailyEmails() throws NotFoundException {
        List<User> users=userService.findAll(User.class);
        for(User user:users){
            if(userService.isSubscribed(user.getId())){
                Integer categoryid=userService.findCategoryIdByUserId(user.getId());
                List<Program> programs= programService.findAllAddedLast24Hours(categoryid);
                emailService.sendEmail(user.getMail(), "Novi programi za kategoriju na koju ste pretplaceni", generateEmailBody(programs));
            }
        }


    }

    private String generateEmailBody(List<Program> programs) {
        StringBuilder sb = new StringBuilder();
        sb.append("Pronađeni su novi programi:\n\n");
        for (Program program : programs) {
            sb.append("Naziv programa: ").append(program.getName()).append("\n");
            sb.append("Opis: ").append(program.getDescription()).append("\n\n");
        }
        return sb.toString();
    }
}
