package com.ss10.model.dto.response;

import com.ss10.model.enums.CreditCardStatus;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CreditCardResponseDTO {
    private UUID id;
    private UUID accountId;
    private Double spendingLimit;
    private Double amountSpent;
    private CreditCardStatus status;
}
