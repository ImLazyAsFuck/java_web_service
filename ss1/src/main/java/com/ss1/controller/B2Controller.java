package com.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/b2")
public class B2Controller{
    @GetMapping
    public String index(){
        return "b2";
    }
}
