package com.productmanagement.service.user;

import com.productmanagement.model.dto.request.UserChangePasswordDTO;
import com.productmanagement.model.dto.request.UserUpdateProfileDTO;
import com.productmanagement.model.dto.response.UserAdminDTO;
import com.productmanagement.model.dto.response.UserProfileDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserAdminDTO getUserById(UUID id);
    List<UserAdminDTO> getAllUsers();
    void disableUser(UUID id);
    void enableUser(UUID id);
    void changePassword(UUID id, UserChangePasswordDTO dto);
    UserProfileDTO getMyProfile();
    void updateMyProfile(UserUpdateProfileDTO dto);
}
