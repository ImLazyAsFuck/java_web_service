package com.ss5.service.user;

import com.ss5.model.entity.User;

import java.util.List;

public interface UserService{
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
