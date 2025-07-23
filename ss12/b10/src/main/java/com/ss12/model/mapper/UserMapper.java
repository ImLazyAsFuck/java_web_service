package com.ss12.model.mapper;

import com.ss12.model.dto.response.UserResponseDTO;
import com.ss12.model.entity.Role;
import com.ss12.model.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        Set<String> roleNames = user.getRoles().stream()
            .map(Role::getRoleName)
            .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.isStatus(),
                roleNames
        );
    }
}
