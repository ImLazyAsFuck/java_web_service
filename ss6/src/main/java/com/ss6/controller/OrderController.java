package com.ss6.controller;

import com.ss6.model.dto.DataResponse;
import com.ss6.model.entity.Order;
import com.ss6.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<DataResponse<Order>> createOrder(@RequestBody Order order) {
        Order created = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new DataResponse<>(orders, HttpStatus.OK));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<DataResponse<List<Order>>> getOrdersByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Order> orders = orderService.getOrdersByDate(date);
        return ResponseEntity.ok(new DataResponse<>(orders, HttpStatus.OK));
    }
}
