package com.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController{
    @GetMapping
    public String index(Model model){
        model.addAttribute("msg", "Hello, Spring Boot API!");
        return "b3";
    }
}
