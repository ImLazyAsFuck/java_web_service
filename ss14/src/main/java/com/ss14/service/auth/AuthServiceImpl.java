package com.ss14.service.auth;

import com.ss14.model.dto.request.OtpVerifyDTO;
import com.ss14.model.dto.request.UserLogin;
import com.ss14.model.dto.request.UserRegister;
import com.ss14.model.dto.response.JWTResponse;
import com.ss14.model.dto.response.UserResponseDTO;
import com.ss14.model.entity.RefreshToken;
import com.ss14.model.entity.Role;
import com.ss14.model.entity.User;
import com.ss14.repo.RoleRepo;
import com.ss14.repo.UserRepo;
import com.ss14.security.jwt.JWTProvider;
import com.ss14.security.principal.CustomUserDetails;
import com.ss14.service.otp.OtpService;
import com.ss14.service.refresh.RefreshTokenService;
import com.ss14.service.refresh.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final RefreshTokenService refreshTokenService;
    private final TokenBlacklistService tokenBlacklistService;
    private final RoleRepo roleRepo;

    @Override
    public void loginWithOtp(UserLogin userLogin) {
        authenticate(userLogin.getUsername(), userLogin.getPassword());
        User user = userRepo.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        otpService.generateAndSendOtp(user);
    }

    @Override
    public JWTResponse verifyOtpAndLogin(OtpVerifyDTO dto, String ip) {
        authenticate(dto.getUsername(), dto.getPassword());
        User user = userRepo.findByUsername(dto.getUsername()).orElseThrow();

        if (!otpService.verifyOtp(user, dto.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        refreshTokenService.manageRefreshTokenLimit(user, ip);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, ip);

        return JWTResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .status(true)
                .token(jwtProvider.generateToken(user.getUsername()))
                .refreshToken(refreshToken.getToken())
                .authorities(
                        CustomUserDetails.fromUserEntityToCustomUserDetails(user).getAuthorities()
                )
                .build();
    }

    @Override
    @Transactional
    public UserResponseDTO register(UserRegister userRegister) {
        if (userRepo.existsByUsername(userRegister.getUsername()))
            throw new RuntimeException("Username already taken");
        if (userRepo.existsByEmail(userRegister.getEmail()))
            throw new RuntimeException("Email already taken");

        Role userRole = roleRepo.findByName(ERole.ROLE_USER.name())
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .roles(Set.of(userRole))
                .build();

        userRepo.save(user);

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }

    @Override
    public UserResponseDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }

    @Override
    public String refreshAccessToken(String refreshToken, String ip) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!refreshTokenService.isValid(token, ip)) {
            throw new RuntimeException("Token expired or IP mismatch");
        }

        return jwtProvider.generateToken(token.getUser().getUsername());
    }

    @Override
    public void logout(CustomUserDetails userDetails, HttpServletRequest request) {
        String token = jwtProvider.extractToken(request);
        tokenBlacklistService.blacklist(token);
        refreshTokenService.deleteByUser(userDetails.getUser());
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
