package com.ss14.service.auth;

import com.ss14.model.dto.request.UserLogin;
import com.ss14.model.dto.response.JWTResponse;

public interface AuthService {
    JWTResponse login(UserLogin userLogin);
}

