package com.productmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAdminDTO {
    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private boolean enabled;
    private Set<String> roles;
}
