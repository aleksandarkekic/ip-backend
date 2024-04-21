package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.*;
import com.example.ip_backend.models.requests.LocationRequest;
import com.example.ip_backend.models.requests.ProgramRequest;
import com.example.ip_backend.models.requests.ProgramRequestFront;
import com.example.ip_backend.security.SecurityConsts;
import com.example.ip_backend.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/programs")
public class ProgramController {
    private final ProgramService programService;
    private final UserService userService;

    private final LocationService locationService;
    private final AttributeService  attributeService;
    private final CategoryService categoryService;

    public ProgramController(ProgramService programService, UserService userService, LocationService locationService, AttributeService attributeService, CategoryService categoryService) {
        this.programService = programService;
        this.userService = userService;
        this.locationService = locationService;
        this.attributeService = attributeService;
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<Program> findAll() throws NotFoundException {
        return programService.findAll(Program.class);
    }

    @GetMapping("/{id}")
    public Program findById(@PathVariable Integer id) throws NotFoundException {

        return programService.findById(id,Program.class);
    }

    @GetMapping("/paginated")
    public Page<Program> findAllPaginated(Pageable page){

        return programService.findAllPaginated(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Program insert(@RequestBody ProgramRequestFront programRequestFront)  {
        try{
        ProgramRequest programRequest=new ProgramRequest();
        Integer locationId=null;
        if (!locationService.existsByName(programRequestFront.getLocationName())){
            LocationRequest locationRequest=new LocationRequest();
            locationRequest.setName(programRequestFront.getLocationName());
            locationService.insert(locationRequest, Location.class);
            locationId=locationService.findIdByName(programRequestFront.getLocationName());
        }else{
            locationId=locationService.findIdByName(programRequestFront.getLocationName());
        }
        programRequest.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        programRequest.setLocationId(locationId);
        programRequest.setUserId(userService.getCurrentId());
        programRequest.setName(programRequestFront.getName());
        programRequest.setDescription(programRequestFront.getDescription());
        programRequest.setAttributeId(attributeService.findIdByName(programRequestFront.getAttributeName()));
        programRequest.setDifficulty(programRequestFront.getDifficulty());
        programRequest.setCategoryId(categoryService.findIdByName(programRequestFront.getCategoryName()));
        programRequest.setPrice(programRequestFront.getPrice());
        programRequest.setDuration(programRequestFront.getDuration());
        programRequest.setContact(programRequestFront.getContact());
        programRequest.setCoach(programRequestFront.getCoach());
        return programService.insert(programRequest, Program.class);
        }catch (Exception e){
            log.error("Exception: "+e);
            return null;
        }
    }

    @GetMapping("/price-between")
    public Page<Program> getAllFilteredByPriceIsBetween(Pageable page, @RequestParam BigDecimal lowest, @RequestParam BigDecimal highest) {
        return programService.getAllByPriceIsBetween(page, lowest,highest);
    }

    @GetMapping("/category-name")
    public Page<Program> getAllByCategoryName(Pageable page, String name){
        return programService.getAllByCategoryName(page,name);
    }
    @GetMapping("/user/{id}")
    public Page<Program> getAllByUserId(@PathVariable Integer id,Pageable page) {
        return programService.getAllByUserId(page,id);
    }

    @GetMapping("/attribute-name")
    public Page<Program> getAllByAttributeName(Pageable page, String name){
        return programService.getAllByAttributeName(page,name);
    }
        @GetMapping("/location-name")
    public Page<Program> getAllByLocationName(Pageable page, String name){
        return programService.getAllByLocationName(page,name);
    }
    @GetMapping("/paginatedByIds")
    public Page<Program> findByIdsPaginated(Pageable page,@RequestParam List<Integer> programIds)  {
        return programService.findByIdPaginated(page,programIds);
    }
    @GetMapping("/last24h")
    public List<Program> findAllAddedLast24Hours(@RequestParam Integer categoryId){
        return programService.findAllAddedLast24Hours(categoryId);
    }
    @GetMapping("/filtered")
    public Page<Program> getAllFilteredPosts(
            Pageable page,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String locationName,
            @RequestParam(required = false) String attributeName,
            @RequestParam(required = false) String search
    ) {

        return programService.getFiltered(page,priceFrom,priceTo,categoryName,locationName,attributeName,search);
    }

    @GetMapping("/max")
    public Integer findMaxId(){
        return programService.findMaxId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Program program = programService.findById(id, Program.class);
            System.out.println(program.getUserUsername() + " " + userService.getCurrentUserInfo().getUsername());
            if (Objects.equals(program.getUserUsername(), userService.getCurrentUserInfo().getUsername()) || userService.getCurrentUser().getRole().equals(SecurityConsts.ADMIN))
                programService.delete(id);
        }catch (Exception e){
            log.error("Exception: "+e);
        }
    }
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Integer id)  {
        try {
            Program program = programService.findById(id, Program.class);
            ProgramRequest programRequest = new ProgramRequest();
            programRequest.setName(program.getName());
            programRequest.setDescription(program.getDescription());
            programRequest.setPrice(program.getPrice());
            programRequest.setDifficulty(program.getDifficulty());
            programRequest.setDuration(program.getDuration());
            programRequest.setActive(false);
            programRequest.setCategoryId(categoryService.findIdByName(program.getCategoryName()));
            programRequest.setAttributeId(attributeService.findIdByName(program.getAttributeName()));
            programRequest.setLocationId(locationService.findIdByName(program.getLocationName()));
            programRequest.setUserId(userService.findIdByUsername(program.getUserUsername()));
            programRequest.setCoach(program.getCoach());
            programRequest.setContact(program.getContact());
            programRequest.setCreatedTime(program.getCreatedTime());
            if (Objects.equals(programRequest.getUserId(), userService.getCurrentId())) {
                programService.update(id, programRequest, Program.class);
                return new ResponseEntity<>("successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("not successfully!", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Exception: "+e);
            return null;
        }
    }
    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activate(@PathVariable Integer id)  {
        try {
        Program program=programService.findById(id,Program.class);
        ProgramRequest programRequest=new ProgramRequest();
        programRequest.setName(program.getName());
        programRequest.setDescription(program.getDescription());
        programRequest.setPrice(program.getPrice());
        programRequest.setDifficulty(program.getDifficulty());
        programRequest.setDuration(program.getDuration());
        programRequest.setActive(true);
        programRequest.setCategoryId(categoryService.findIdByName(program.getCategoryName()));
        programRequest.setAttributeId(attributeService.findIdByName(program.getAttributeName()));
        programRequest.setLocationId(locationService.findIdByName(program.getLocationName()));
        programRequest.setUserId(userService.findIdByUsername(program.getUserUsername()));
        programRequest.setCoach(program.getCoach());
        programRequest.setContact(program.getContact());
        programRequest.setCreatedTime(program.getCreatedTime());
        if (Objects.equals(programRequest.getUserId(), userService.getCurrentId())){
            programService.update(id,programRequest,Program.class);
            return new ResponseEntity<>("successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("not successfully!", HttpStatus.NOT_FOUND);
        }
        }catch (Exception e){
            log.error("Exception: "+e);
            return null;
        }
    }

}
