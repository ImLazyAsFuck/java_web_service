package com.ss8.controller;

import com.ss8.model.dto.PaymentSlipDTO;
import com.ss8.service.paymentslip.PaymentSlipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
@RequiredArgsConstructor
public class PaymentSlipController {

    private final PaymentSlipService paymentSlipService;

    @PostMapping
    public ResponseEntity<PaymentSlipDTO> create(@RequestBody PaymentSlipDTO dto) {
        return ResponseEntity.ok(paymentSlipService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentSlipDTO> update(@PathVariable Long id, @RequestBody PaymentSlipDTO dto) {
        return ResponseEntity.ok(paymentSlipService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentSlipDTO>> getAll() {
        return ResponseEntity.ok(paymentSlipService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentSlipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
