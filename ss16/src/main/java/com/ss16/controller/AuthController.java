package com.ss16.controller;

import com.ss16.model.dto.*;
import com.ss16.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginDTO dto,
                                             HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        return ResponseEntity.ok(authService.login(dto, ip));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JWTResponse> refresh(@RequestBody RefreshTokenRequest request,
                                               HttpServletRequest httpRequest) {
        String ip = httpRequest.getRemoteAddr();
        return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken(), ip));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        authService.logout(request.getUsername());
        return ResponseEntity.ok("Đã đăng xuất");
    }
}
