package com.babotea.controller;

import com.babotea.model.dto.request.UpdateRoleRequest;
import com.babotea.model.dto.response.APIResponse;
import com.babotea.model.entity.User;
import com.babotea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<APIResponse<String>> updateRole(@PathVariable Long id,
                                                          @RequestBody UpdateRoleRequest request) {
        userService.updateUserRole(id, request.getNewRole());
        return ResponseEntity.ok(
                APIResponse.<String>builder()
                        .success(true)
                        .message("Cập nhật quyền thành công")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<APIResponse<User>> getCurrentUser() {
        return ResponseEntity.ok(
                APIResponse.<User>builder()
                        .success(true)
                        .message("Thông tin người dùng")
                        .status(HttpStatus.OK)
                        .data(userService.getCurrentUser())
                        .build()
        );
    }
}
