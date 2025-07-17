package com.ss8.controller;

import com.ss8.service.statistical.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/top-dishes")
    public ResponseEntity<?> topDishes() {
        return ResponseEntity.ok(statisticalService.topDishes());
    }

    @GetMapping("/top-customers")
    public ResponseEntity<?> topCustomers() {
        return ResponseEntity.ok(statisticalService.topCustomers());
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<?> currentMonthExpenses() {
        return ResponseEntity.ok(statisticalService.currentMonthExpenses());
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<?> monthlyExpenses() {
        return ResponseEntity.ok(statisticalService.monthlyExpenses());
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<?> monthlyRevenue() {
        return ResponseEntity.ok(statisticalService.monthlyRevenue());
    }

    @GetMapping("/top-employee")
    public ResponseEntity<?> topEmployee() {
        return ResponseEntity.ok(statisticalService.topEmployeeThisMonth());
    }
}
