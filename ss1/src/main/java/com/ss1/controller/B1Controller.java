package com.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/b1")
public class B1Controller{
    @GetMapping
    public String index(){
        return "b1";
    }
}
