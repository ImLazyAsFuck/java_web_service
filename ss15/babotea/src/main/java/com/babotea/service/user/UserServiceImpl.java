package com.babotea.service.user;

import com.babotea.model.entity.User;
import com.babotea.model.enums.Role;
import com.babotea.repo.UserRepo;
import com.babotea.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public void updateUserRole(Long userId, String newRole) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        user.setRole(Role.valueOf(newRole));
        userRepo.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser();
        }
        throw new RuntimeException("Không xác định được người dùng hiện tại");
    }
}
