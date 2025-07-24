package com.b910.controller;

import com.b910.model.dto.request.CheckoutRequest;
import com.b910.model.dto.response.APIResponse;
import com.b910.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<APIResponse<?>> checkout(@RequestBody CheckoutRequest request) {
        orderService.checkout(request);
        return ResponseEntity.ok(new APIResponse<>(true, "Order placed successfully", null, HttpStatus.OK));
    }
}
