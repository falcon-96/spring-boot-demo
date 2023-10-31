package com.self.probe.controller;

import com.self.probe.model.Droid;
import com.self.probe.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<List<Map<String, Object>>> getAllDroids() {
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


    @GetMapping("/hello")
    public String testHello() {
        return "Hello, World!";
    }
}
