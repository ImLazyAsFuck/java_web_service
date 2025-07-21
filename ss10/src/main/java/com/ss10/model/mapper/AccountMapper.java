package com.ss10.model.mapper;

import com.ss10.model.dto.request.AccountDTO;
import com.ss10.model.dto.response.AccountResponseDTO;
import com.ss10.model.entity.Account;
import com.ss10.model.enums.AccountStatus;

public class AccountMapper {

    public static Account toEntity(AccountDTO dto) {
        return Account.builder()
                .fullName(dto.getFullName())
                .cccd(dto.getCccd())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .money(dto.getMoney())
                .status(AccountStatus.ACTIVE)
                .build();
    }

    public static AccountResponseDTO toDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getFullName(),
                account.getCccd(),
                account.getPhone(),
                account.getMoney(),
                account.getEmail(),
                account.getStatus()
        );
    }

    public static void updateEntity(Account entity, AccountDTO dto) {
        entity.setFullName(dto.getFullName());
        entity.setCccd(dto.getCccd());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setMoney(dto.getMoney());

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
    }
}
