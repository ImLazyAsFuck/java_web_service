package com.ss16.service;

import com.ss16.model.dto.JWTResponse;
import com.ss16.model.dto.LoginDTO;
import com.ss16.model.dto.UserRegisterDTO;
import com.ss16.model.entity.Role;
import com.ss16.model.entity.User;
import com.ss16.model.entity.UserRefreshToken;
import com.ss16.repository.UserRefreshTokenRepository;
import com.ss16.repository.UserRepository;
import com.ss16.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserRepository userRepository;
    private final UserRefreshTokenRepository refreshTokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public void register(UserRegisterDTO dto){
        if(userRepository.existsByUsername(dto.getUsername()))
            throw new RuntimeException("Username đã tồn tại");
        if(userRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email đã tồn tại");

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isLogin(false)
                .isStatus(true)
                .roles(Set.of(new Role(null, "ROLE_USER")))
                .build();

        userRepository.save(user);
    }

    public JWTResponse login(LoginDTO dto, String ipAddress){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        user.setIsLogin(true);
        userRepository.save(user);

        String accessToken = jwtUtil.generateToken(auth.getName());
        String refreshToken = UUID.randomUUID().toString();

        refreshTokenRepo.save(UserRefreshToken.builder()
                .user(user)
                .tokenRefesh(refreshToken)
                .ipAddress(ipAddress)
                .build());

        return new JWTResponse(accessToken, refreshToken);
    }

    public JWTResponse refreshAccessToken(String refreshToken, String ipAddress){
        UserRefreshToken storedToken = refreshTokenRepo
                .findByTokenRefeshAndIpAddress(refreshToken, ipAddress)
                .orElseThrow(() -> new RuntimeException("Token hoặc IP không hợp lệ"));

        String accessToken = jwtUtil.generateToken(storedToken.getUser().getUsername());

        return new JWTResponse(accessToken, refreshToken);
    }

    public void logout(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        user.setIsLogin(false);
        userRepository.save(user);
        refreshTokenRepo.deleteByUser(user);
    }
}
