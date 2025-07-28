package com.babotea.service.user;

import com.babotea.model.entity.User;

public interface UserService {
    void updateUserRole(Long userId, String newRole);
    User getCurrentUser();
}
