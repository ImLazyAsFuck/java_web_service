package com.ss12.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private/hello")
    public String privateHello() {
        return "This is a protected endpoint.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "This is a protected endpoint.";
    }
}
