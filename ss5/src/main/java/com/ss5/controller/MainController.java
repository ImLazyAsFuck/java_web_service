package com.ss5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController{
    @RequestMapping
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

    @GetMapping("/b3")
    public String b3(){
        return "b3";
    }
}
