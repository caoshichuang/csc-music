package com.example.cscmusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DefaultController {
    @GetMapping("first")
    public String sayHello() {
        return "hello";
    }
}
