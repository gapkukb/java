package com.fafafa.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")

public class HelloController {
    @GetMapping("hello")
    private String hello() {
        return "hello";
    }
}
