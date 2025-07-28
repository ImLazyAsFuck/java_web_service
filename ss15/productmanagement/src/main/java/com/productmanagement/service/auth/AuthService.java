package com.productmanagement.service.auth;

import com.productmanagement.model.dto.request.LoginRequest;
import com.productmanagement.model.dto.request.UserRegisterDTO;
import com.productmanagement.model.dto.response.JWTResponse;

public interface AuthService {
    JWTResponse login(LoginRequest request);
    String register(UserRegisterDTO request);
}
