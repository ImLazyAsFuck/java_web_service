package com.ss8.controller;

import com.ss8.model.dto.CustomerDTO;
import com.ss8.model.entity.Customer;
import com.ss8.model.dto.ApiResponse;
import com.ss8.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> create(@RequestBody CustomerDTO dto) {
        Customer created = customerService.create(dto);
        return ResponseEntity.ok(
                ApiResponse.<Customer>builder()
                        .status(true)
                        .message("Customer created successfully")
                        .data(created)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Customer updated = customerService.update(id, dto);
        return ResponseEntity.ok(
                ApiResponse.<Customer>builder()
                        .status(true)
                        .message("Customer updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .status(true)
                        .message("Customer deleted successfully (soft delete)")
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAll() {
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(
                ApiResponse.<List<Customer>>builder()
                        .status(true)
                        .message("List of active customers")
                        .data(customers)
                        .build()
        );
    }
}
