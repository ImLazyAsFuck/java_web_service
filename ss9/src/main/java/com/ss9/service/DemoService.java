package com.ss9.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        return "Xin chào, " + name + "!";
    }
}
