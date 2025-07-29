package com.ss16.controller;

import com.ss16.model.entity.Combo;
import com.ss16.service.combo.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
public class ComboController {

    private final ComboService comboService;

    @GetMapping
    public ResponseEntity<List<Combo>> getAllCombos() {
        return ResponseEntity.ok(comboService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Combo> create(@RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.create(combo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Combo> update(@PathVariable Long id, @RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.update(id, combo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        comboService.delete(id);
        return ResponseEntity.ok("Đã xóa combo");
    }
}
