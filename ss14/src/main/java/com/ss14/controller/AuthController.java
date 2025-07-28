package com.ss14.controller;

import com.ss14.model.dto.request.OtpVerifyDTO;
import com.ss14.model.dto.request.UserLogin;
import com.ss14.model.dto.request.UserRegister;
import com.ss14.model.dto.response.APIResponse;
import com.ss14.model.dto.response.JWTResponse;
import com.ss14.model.dto.response.UserResponseDTO;
import com.ss14.security.principal.CustomUserDetails;
import com.ss14.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody UserLogin dto) {
        authService.loginWithOtp(dto);
        return ResponseEntity.ok(new APIResponse<>(true, "OTP has been sent to your email", null, HttpStatus.OK));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<APIResponse<JWTResponse>> verifyOtp(@RequestBody OtpVerifyDTO dto, HttpServletRequest request) {
        JWTResponse tokens = authService.verifyOtpAndLogin(dto, request.getRemoteAddr());
        return ResponseEntity.ok(new APIResponse<>(true, "Login successful", tokens, HttpStatus.OK));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserResponseDTO>> register(@Valid @RequestBody UserRegister registerRequest) {
        UserResponseDTO userDTO = authService.register(registerRequest);
        return ResponseEntity.ok(new APIResponse<>(true, "Registration successful", userDTO, HttpStatus.CREATED));
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse<UserResponseDTO>> getCurrentUser() {
        UserResponseDTO currentUser = authService.getCurrentUser();
        return ResponseEntity.ok(new APIResponse<>(true, "Current user retrieved", currentUser, HttpStatus.OK));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<APIResponse<Map<String, String>>> refresh(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String refreshToken = body.get("refreshToken");
        String clientIp = request.getRemoteAddr();
        String newAccessToken = authService.refreshAccessToken(refreshToken, clientIp);
        return ResponseEntity.ok(new APIResponse<>(true, "New access token generated", Map.of("accessToken", newAccessToken), HttpStatus.OK));
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<String>> logout(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.logout(userDetails, request);
        return ResponseEntity.ok(new APIResponse<>(true, "Logout successful", null, HttpStatus.OK));
    }
}
