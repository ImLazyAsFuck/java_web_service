package com.ss10.model.dto.response;

import com.ss10.model.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private UUID id;
    private String fullName;
    private String cccd;
    private String phone;
    private Double money;
    private String email;
    private AccountStatus status;
}
