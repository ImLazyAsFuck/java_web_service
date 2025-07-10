package com.ss3.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error-page")
    public String showErrorPage() {
        return "error/error-page";
    }
}
