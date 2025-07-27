package com.ss14.controller;

import com.ss14.model.dto.request.OtpVerifyDTO;
import com.ss14.model.dto.request.UserLogin;
import com.ss14.model.dto.request.UserRegister;
import com.ss14.model.dto.response.APIResponse;
import com.ss14.model.dto.response.JWTResponse;
import com.ss14.model.dto.response.UserResponseDTO;
import com.ss14.model.entity.RefreshToken;
import com.ss14.model.entity.User;
import com.ss14.repo.UserRepo;
import com.ss14.security.jwt.JWTProvider;
import com.ss14.security.principal.CustomUserDetails;
import com.ss14.service.auth.AuthService;
import com.ss14.service.otp.OtpService;
import com.ss14.service.refresh.RefreshTokenService;
import com.ss14.service.refresh.TokenBlacklistService;
import com.ss14.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final JWTProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserRepo userRepo;
    private final OtpService otpService;
    private final AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepo.findByUsername(dto.getUsername()).orElseThrow();

        otpService.generateAndSendOtp(user);

        return ResponseEntity.ok("OTP has been sent to your email.");
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyDTO dto, HttpServletRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepo.findByUsername(dto.getUsername()).orElseThrow();
        if (!otpService.verifyOtp(user, dto.getOtp())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }

        String accessToken = jwtProvider.generateToken(String.valueOf(user));
        String ip = request.getRemoteAddr();
        refreshTokenService.manageRefreshTokenLimit(user, ip);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, ip);
        return ResponseEntity.ok(new JWTResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserResponseDTO>> register(@Valid @RequestBody UserRegister registerRequest) {
        UserResponseDTO userDTO = userService.registerUser(registerRequest);
        return ResponseEntity.ok(
            new APIResponse<>(true, "Registration successful", userDTO, HttpStatus.CREATED)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse<UserResponseDTO>> getCurrentUser() {
        UserResponseDTO currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(
            new APIResponse<>(true, "Current user retrieved", currentUser, HttpStatus.OK)
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String refreshToken = body.get("refreshToken");
        String clientIp = request.getRemoteAddr();

        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!refreshTokenService.isValid(token, clientIp)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired or IP mismatch");
        }

        String newAccessToken = jwtProvider.generateToken(String.valueOf(token.getUser()));
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String token = jwtProvider.extractToken(request);
        tokenBlacklistService.blacklist(token);
        refreshTokenService.deleteByUser(userDetails.getUser());

        return ResponseEntity.ok("Logout successful");
    }
}
