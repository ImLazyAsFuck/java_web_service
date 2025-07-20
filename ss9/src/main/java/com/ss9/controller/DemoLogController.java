package com.ss9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo-log")
public class DemoLogController {

    private static final Logger logger = LoggerFactory.getLogger(DemoLogController.class);

    @GetMapping("/trace")
    public String traceLog() {
        logger.trace("Đã ghi ss9 trace");
        return "Trace ss9 đã ghi!";
    }

    @GetMapping("/debug")
    public String debugLog() {
        logger.debug("Đã ghi ss9 debug");
        return "Debug ss9 đã ghi!";
    }

    @GetMapping("/info")
    public String infoLog() {
        logger.info("Đã ghi ss9 info");
        return "Info ss9 đã ghi!";
    }

    @GetMapping("/warning")
    public String warnLog() {
        logger.warn("Đã ghi ss9 warning");
        return "Warning ss9 đã ghi!";
    }

    @GetMapping("/error")
    public String errorLog() {
        logger.error("Đã ghi ss9 error");
        return "Error ss9 đã ghi!";
    }
}
