package com.self.probe.controller;

import com.self.probe.model.DroidList;
import com.self.probe.model.JsonResponse;
import com.self.probe.service.ApplicationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final ApplicationService service;


    public MainController(ApplicationService service) {
        this.service = service;
    }


    @GetMapping("/hello")
    public String intro() {
        return "Hello World";
    }

    @PostMapping("/createDroid")
    public ResponseEntity<String> createDroid(@RequestParam(value = "name") String name) {
        return service.createNewDroid(name);
    }

    @GetMapping("showAllDroids")
    public ResponseEntity<DroidList> getAllDroids() {
        return service.getAllDroids();
    }

    @PutMapping("/editDroid")
    public ResponseEntity<String> updateName(@RequestParam("existingName") String existingName,
                                             @RequestParam("newName") String newName) {
        return service.updateName(existingName, newName);
    }

    @DeleteMapping("/deleteDroid")
    public ResponseEntity<String> deleteDroid(@RequestParam("name")String name)
    {
        return service.deleteDroid(name);
    }
}
