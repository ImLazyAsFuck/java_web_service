package com.productmanagement.model.mapper;

import com.productmanagement.model.dto.response.UserAdminDTO;
import com.productmanagement.model.dto.response.UserProfileDTO;
import com.productmanagement.model.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    
    public static UserAdminDTO toAdminDTO(User user) {
        return UserAdminDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .fullName(user.getFullName())
            .email(user.getEmail())
            .address(user.getAddress())
            .phone(user.getPhone())
            .enabled(user.isEnabled())
            .roles(user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet()))
            .build();
    }

    public static UserProfileDTO toProfileDTO(User user) {
        return UserProfileDTO.builder()
            .username(user.getUsername())
            .fullName(user.getFullName())
            .email(user.getEmail())
            .address(user.getAddress())
            .phone(user.getPhone())
            .build();
    }
}
