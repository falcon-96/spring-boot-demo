package com.self.probe.controller;

import com.self.probe.model.Droid;
import com.self.probe.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final ApplicationService service;

    public MainController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping("/createDroid")
    public ResponseEntity<String> createDroid(@Validated @RequestBody Droid droid) {
        return service.createNewDroid(droid);
    }

    @GetMapping("showAllDroids")
    public ResponseEntity<?> getAllDroids() {
        return service.getAllDroids();
    }

    @PutMapping("/editDroid")
    public ResponseEntity<String> updateName(@Validated @RequestBody Droid droid) {
        return service.updateName(droid);
    }

    @DeleteMapping("/deleteDroid")
    public ResponseEntity<String> deleteDroid(@RequestParam("name") String name) {
        return service.deleteDroid(name);
    }
}
