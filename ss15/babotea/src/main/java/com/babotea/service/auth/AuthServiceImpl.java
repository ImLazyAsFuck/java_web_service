package com.babotea.service.auth;

import com.babotea.model.dto.request.LoginRequest;
import com.babotea.model.dto.request.RegisterRequest;
import com.babotea.model.dto.response.JWTResponse;
import com.babotea.model.entity.User;
import com.babotea.model.enums.Role;
import com.babotea.repo.UserRepo;
import com.babotea.security.jwt.JWTProvider;
import com.babotea.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JWTProvider jwtUtil;
    private final AuthenticationManager authManager;

    public void register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email đã tồn tại");

        User user = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(Role.valueOf("ROLE_USER"))
                .build();
        userRepo.save(user);
    }

    public JWTResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(String.valueOf(user));
        return new JWTResponse(token, "dummy-refresh-token");
    }
}
