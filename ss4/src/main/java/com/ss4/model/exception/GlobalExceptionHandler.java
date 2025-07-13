//package com.ss4.model.exception;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public String handleIllegalArgument(IllegalArgumentException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/custom-error";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleGeneral(Exception ex, Model model) {
//        model.addAttribute("error", "Đã xảy ra lỗi không xác định: " + ex.getMessage());
//        return "error/custom-error";
//    }
//}
