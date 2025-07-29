package com.ss16.controller;

import com.ss16.model.entity.PlayArea;
import com.ss16.service.playarea.PlayAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play-areas")
@RequiredArgsConstructor
public class PlayAreaController {

    private final PlayAreaService service;

    @GetMapping
    public ResponseEntity<List<PlayArea>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<PlayArea> create(@RequestBody PlayArea area) {
        return ResponseEntity.ok(service.create(area));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<PlayArea> update(@PathVariable Long id, @RequestBody PlayArea area) {
        return ResponseEntity.ok(service.update(id, area));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Đã xóa khu vui chơi");
    }
}
