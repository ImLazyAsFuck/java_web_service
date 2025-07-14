package com.ss5.service.user;

import com.ss5.model.entity.User;
import com.ss5.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;


    @Override
    public User createUser(User user){
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Long id, User user){
        User existing = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        return userRepo.save(existing);
    }

    @Override
    public void deleteUser(Long id){
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }
}
