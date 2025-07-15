package com.ss6.service.user;

import com.ss6.model.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(User user);
    void deleteById(Long id);
}
