package com.ss14.service.auth;

import com.ss14.model.dto.request.OtpVerifyDTO;
import com.ss14.model.dto.request.UserLogin;
import com.ss14.model.dto.request.UserRegister;
import com.ss14.model.dto.response.JWTResponse;
import com.ss14.model.dto.response.UserResponseDTO;
import com.ss14.security.principal.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    void loginWithOtp(UserLogin userLogin);
    JWTResponse verifyOtpAndLogin(OtpVerifyDTO dto, String ipAddress);
    UserResponseDTO register(UserRegister registerRequest);
    UserResponseDTO getCurrentUser();
    String refreshAccessToken(String refreshToken, String ip);
    void logout(CustomUserDetails userDetails, HttpServletRequest request);
}
