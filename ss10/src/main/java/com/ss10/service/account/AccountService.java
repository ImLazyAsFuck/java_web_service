package com.ss10.service.account;

import com.ss10.model.dto.request.AccountDTO;
import com.ss10.model.dto.response.AccountResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponseDTO createAccount(AccountDTO dto);
    AccountResponseDTO updateAccount(UUID id, AccountDTO dto);
    void deleteAccount(UUID id);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO getByCccd(String cccd);
}
