package com.ss16.repository;

import com.ss16.model.entity.User;
import com.ss16.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long>{
    Optional<UserRefreshToken> findByTokenRefeshAndIpAddress(String token, String ipAddress);
    void deleteByUser(User user);
}