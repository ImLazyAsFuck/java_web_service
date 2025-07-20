package com.ss9.controller;

import com.ss9.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/b6")
@RequiredArgsConstructor
public class B6Controller{

    private final DemoService demoService;

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", required = false) String name) {
        return demoService.greet(name);
    }
}
