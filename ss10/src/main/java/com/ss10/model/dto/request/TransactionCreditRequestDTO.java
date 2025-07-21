package com.ss10.model.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreditRequestDTO {
    private UUID creditCardId;
    private UUID accountReceiverId;
    private Double money;
    private String note;
}
