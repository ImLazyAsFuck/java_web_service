package com.babotea.service.auth;

import com.babotea.model.dto.request.LoginRequest;
import com.babotea.model.dto.request.RegisterRequest;
import com.babotea.model.dto.response.JWTResponse;

public interface AuthService{
    void register(RegisterRequest req);
    JWTResponse login(LoginRequest req);
}
