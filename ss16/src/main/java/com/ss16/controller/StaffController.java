package com.ss16.controller;

import com.ss16.model.dto.StaffCreateDTO;
import com.ss16.model.dto.StaffDTO;
import com.ss16.model.dto.StaffUpdateDTO;
import com.ss16.model.dto.UpdateRoleDTO;
import com.ss16.service.staff.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffCreateDTO dto) {
        return ResponseEntity.ok(staffService.createStaff(dto));
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StaffDTO> updateRole(@PathVariable Long id, @RequestBody UpdateRoleDTO dto) {
        return ResponseEntity.ok(staffService.updateRole(id, dto.getRole()));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<StaffDTO> getMyInfo() {
        return ResponseEntity.ok(staffService.getMyInfo());
    }

    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<StaffDTO> updateMyInfo(@RequestBody StaffUpdateDTO dto) {
        return ResponseEntity.ok(staffService.updateMyInfo(dto));
    }
}
