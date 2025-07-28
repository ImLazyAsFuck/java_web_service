package com.productmanagement.controller;

import com.productmanagement.model.dto.request.LoginRequest;
import com.productmanagement.model.dto.request.UserRegisterDTO;
import com.productmanagement.model.dto.response.APIResponse;
import com.productmanagement.model.dto.response.JWTResponse;
import com.productmanagement.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody @Valid LoginRequest request) {
        JWTResponse jwt = authService.login(request);
        APIResponse<JWTResponse> response = new APIResponse<>(
                true,
                "Login successful",
                jwt,
                HttpStatus.OK
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody @Valid UserRegisterDTO request) {
        String result = authService.register(request);
        APIResponse<String> response = new APIResponse<>(
                true,
                "User registered successfully",
                result,
                HttpStatus.CREATED
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
