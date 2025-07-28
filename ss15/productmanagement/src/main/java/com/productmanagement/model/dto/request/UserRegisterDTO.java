package com.productmanagement.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

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
