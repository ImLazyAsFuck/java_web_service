package com.ss12.service.role;

import com.ss12.model.entity.Role;

import java.util.Set;

public interface RoleService {
    Role findByName(String roleName);
    Set<Role> getDefaultRoles();
}
