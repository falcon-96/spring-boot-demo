package com.self.probe.controller;

import com.self.probe.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final ApplicationService service;

    @Autowired
    public MainController(ApplicationService service) {
        this.service = service;
    }


    @GetMapping("/hello")
    public String intro() {
        return "Hello World";
    }

    @PostMapping("/createDroid")
    public ResponseEntity<?> createDroid(@RequestParam(value = "name") String name) {
        return service.createNewDroid(name);
    }
}
