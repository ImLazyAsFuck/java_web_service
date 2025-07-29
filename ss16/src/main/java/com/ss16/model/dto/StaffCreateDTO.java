package com.ss16.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffCreateDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;
}
