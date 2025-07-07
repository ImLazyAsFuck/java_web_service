package com.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/b4")
public class B4Controller{
    @GetMapping
    public String index(){
        return "b4";
    }
}
