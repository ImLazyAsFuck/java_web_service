package com.ss4.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController{
    @RequestMapping
    public String index(){
        return "index";
    }
}
