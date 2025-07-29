package com.ss16.controller;

import com.ss16.model.dto.TicketOrderRequestDTO;
import com.ss16.model.dto.TicketOrderResponseDTO;
import com.ss16.service.ticketorder.TicketOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ticket-orders")
@RequiredArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TicketOrderResponseDTO> placeOrder(@RequestBody @Valid TicketOrderRequestDTO dto) {
        return ResponseEntity.ok(ticketOrderService.placeOrder(dto));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TicketOrderResponseDTO>> getMyOrders() {
        return ResponseEntity.ok(ticketOrderService.getMyOrders());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<TicketOrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(ticketOrderService.getAllOrders());
    }
}
