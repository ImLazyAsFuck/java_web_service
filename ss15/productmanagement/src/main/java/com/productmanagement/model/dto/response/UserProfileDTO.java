package com.productmanagement.model.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {
    private String username;
    private String fullName;
    private String email;
    private String address;
    private String phone;
}
