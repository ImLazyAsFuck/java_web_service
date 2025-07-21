package com.ss10.model.mapper;

import com.ss10.model.dto.response.CreditCardResponseDTO;
import com.ss10.model.entity.CreditCard;

public class CreditCardMapper {

    public static CreditCardResponseDTO toDTO(CreditCard card) {
        return CreditCardResponseDTO.builder()
                .id(card.getId())
                .accountId(card.getAccount().getId())
                .spendingLimit(card.getSpendingLimit())
                .amountSpent(card.getAmountSpent())
                .status(card.getStatus())
                .build();
    }
}
