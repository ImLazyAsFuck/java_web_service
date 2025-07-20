package com.ss9.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/b7")
@RequiredArgsConstructor
public class B7Controller{

    private static final Logger logger = LoggerFactory.getLogger(B7Controller.class);

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "Guest") String name) {
        logger.info("Gửi lời chào tới người dùng: {}", name);
        return "Hello, " + name;
    }

    @GetMapping("/error")
    public String causeError() {
        try {
            int x = 10 / 0;
            return "Won't reach here";
        } catch (Exception e) {
            logger.error("Đã xảy ra lỗi khi chia số", e);
            throw new RuntimeException("Lỗi chia cho 0");
        }
    }
}
