package com.productmanagement.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateProfileDTO {
    @NotBlank
    @Size(max = 50)
    private String fullName;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    @Size(max = 15)
    private String phone;
}
