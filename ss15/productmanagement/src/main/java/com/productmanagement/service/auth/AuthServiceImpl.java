package com.productmanagement.service.auth;

import com.productmanagement.model.dto.request.LoginRequest;
import com.productmanagement.model.dto.request.UserRegisterDTO;
import com.productmanagement.model.dto.response.JWTResponse;
import com.productmanagement.model.entity.Role;
import com.productmanagement.model.entity.User;
import com.productmanagement.repo.RoleRepo;
import com.productmanagement.repo.UserRepo;
import com.productmanagement.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtService;
    private final RoleRepo roleRepo;


    @Override
    public JWTResponse login(LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        String accessToken = jwtService.generateAccessToken(user);

        return JWTResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .status(user.isEnabled())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .accessToken(accessToken)
                .build();
    }

    @Override
    public String register(UserRegisterDTO dto) {
        if (userRepo.existsByUsername(dto.getUsername()))
            throw new RuntimeException("Username đã tồn tại");

        if (userRepo.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email đã tồn tại");

        Role roleUser = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER chưa tồn tại trong DB"));

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .enabled(true)
                .roles(Set.of(roleUser))
                .build();

        userRepo.save(user);
        return "Đăng ký thành công.";
    }

}
