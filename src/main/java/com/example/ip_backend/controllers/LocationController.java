package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Location;
import com.example.ip_backend.models.dto.SingleLocation;
import com.example.ip_backend.models.requests.LocationRequest;
import com.example.ip_backend.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public List<Location> findAll() throws NotFoundException {
        return locationService.findAll(Location.class);
    }

    @GetMapping("/{id}")
    public SingleLocation findById(@PathVariable Integer id) throws NotFoundException {
        return locationService.findById(id,SingleLocation.class);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Location insert(@RequestBody LocationRequest locationRequest) throws NotFoundException {
        return locationService.insert(locationRequest,Location.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws NotFoundException {
        locationService.delete(id);
    }
    @GetMapping("distinct-names")
    public List<String> getAllDistinctNames(){
        return locationService.getAllDistinctNames();
    }
}
