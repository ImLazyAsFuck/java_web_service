package com.ss10.controller;

import com.ss10.model.dto.request.CreditCardDTO;
import com.ss10.model.entity.CreditCard;
import com.ss10.service.creditcard.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/creditcards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> create(@RequestBody CreditCardDTO dto) {
        CreditCard created = creditCardService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateLimit(@PathVariable UUID id,
                                                  @RequestBody CreditCardDTO dto) {
        CreditCard updated = creditCardService.updateLimit(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(@PathVariable UUID id,
                                                   @RequestBody CreditCardDTO dto) {
        CreditCard updated = creditCardService.updateStatus(id, dto);
        return ResponseEntity.ok(updated);
    }
}
