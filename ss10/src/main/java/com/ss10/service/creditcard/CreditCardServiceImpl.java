package com.ss10.service.creditcard;

import com.ss10.model.dto.request.CreditCardDTO;
import com.ss10.model.entity.Account;
import com.ss10.model.entity.CreditCard;
import com.ss10.model.enums.CreditCardStatus;
import com.ss10.repo.AccountRepo;
import com.ss10.repo.CreditCardRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepo creditCardRepo;
    private final AccountRepo accountRepo;

    @Override
    @Transactional
    public CreditCard create(CreditCardDTO dto) {
        Account account = accountRepo.findById(dto.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (creditCardRepo.existsByAccount(account)) {
            throw new IllegalArgumentException("This account already has a credit card.");
        }

        CreditCard card = CreditCard.builder()
                .account(account)
                .spendingLimit(dto.getSpendingLimit())
                .amountSpent(0.0)
                .status(CreditCardStatus.ACTIVE)
                .build();

        return creditCardRepo.save(card);
    }

    @Override
    @Transactional
    public CreditCard updateLimit(UUID id, CreditCardDTO dto) {
        CreditCard card = creditCardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found"));

        card.setSpendingLimit(dto.getSpendingLimit());
        return creditCardRepo.save(card);
    }

    @Override
    @Transactional
    public CreditCard updateStatus(UUID id, CreditCardDTO dto) {
        if (dto.getStatus() == null) {
            throw new IllegalArgumentException("Status must not be null");
        }

        CreditCard card = creditCardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found"));

        card.setStatus(dto.getStatus());
        return creditCardRepo.save(card);
    }
}
