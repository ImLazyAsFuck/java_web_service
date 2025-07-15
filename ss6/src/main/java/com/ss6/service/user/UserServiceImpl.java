package com.ss6.service.user;

import com.ss6.model.entity.User;
import com.ss6.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Override
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    @Override
    public User save(User user){
        return userRepo.save(user);
    }

    @Override
    public User update(User user){
        User existingUser =findById(user.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepo.save(existingUser);
    }
}
