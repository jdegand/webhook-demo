package com.example.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RonaldoSchoolController {

    @GetMapping(path = "/webhook/studentAdded/{name}")
    public String studentAdded(@PathVariable String name) {
        System.out.println("Student name: " + name);
        return "Webhook Received";
    }

}
