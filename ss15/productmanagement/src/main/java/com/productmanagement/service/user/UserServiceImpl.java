package com.productmanagement.service.user;

import com.productmanagement.model.dto.request.UserChangePasswordDTO;
import com.productmanagement.model.dto.request.UserUpdateProfileDTO;
import com.productmanagement.model.dto.response.UserAdminDTO;
import com.productmanagement.model.dto.response.UserProfileDTO;
import com.productmanagement.model.entity.User;
import com.productmanagement.model.mapper.UserMapper;
import com.productmanagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAdminDTO getUserById(UUID id) {
        User user = userRepo.findById(id).orElseThrow();
        return UserMapper.toAdminDTO(user);
    }

    @Override
    public List<UserAdminDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toAdminDTO)
                .toList();
    }

    @Override
    public void disableUser(UUID id) {
        User user = userRepo.findById(id).orElseThrow();
        user.setEnabled(false);
        userRepo.save(user);
    }

    @Override
    public void enableUser(UUID id) {
        User user = userRepo.findById(id).orElseThrow();
        user.setEnabled(true);
        userRepo.save(user);
    }

    @Override
    public void changePassword(UUID id, UserChangePasswordDTO dto) {
        User user = userRepo.findById(id).orElseThrow();
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword()))
            throw new RuntimeException("Mật khẩu cũ không đúng");

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepo.save(user);
    }

    @Override
    public UserProfileDTO getMyProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username).orElseThrow();
        return UserMapper.toProfileDTO(user);
    }

    @Override
    public void updateMyProfile(UserUpdateProfileDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username).orElseThrow();

        user.setFullName(dto.getFullName());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());

        userRepo.save(user);
    }
}
