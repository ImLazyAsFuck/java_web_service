package com.ss3.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid phone number")
    private String phone;

    @Positive(message = "Salary must be greater than zero")
    @NotNull(message = "Salary must not be null")
    private double salary;
}
