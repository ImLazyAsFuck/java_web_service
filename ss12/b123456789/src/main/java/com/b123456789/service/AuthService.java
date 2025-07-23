package com.b123456789.service;

import com.b123456789.model.dto.RegisterRequest;
import com.b123456789.model.entity.User;
import com.b123456789.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return "Username already exists!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setStatus(true);

        userRepo.save(user);
        return "Register successfully!";
    }
}