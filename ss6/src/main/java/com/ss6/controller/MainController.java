package com.ss6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController{
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/b1")
    public String b1(){
        return "b1";
    }

    @GetMapping("/b2")
    public String b2(){
        return "b2";
    }
}
