package com.ss10.model.dto.response;

import com.ss10.model.enums.TransactionStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreditResponseDTO {
    private UUID id;
    private UUID creditCardSenderId;
    private UUID accountReceiverId;
    private Double money;
    private String note;
    private TransactionStatus status;
}
