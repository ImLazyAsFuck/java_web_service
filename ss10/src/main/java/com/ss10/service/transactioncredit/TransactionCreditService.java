package com.ss10.service.transactioncredit;

import com.ss10.model.dto.request.TransactionCreditRequestDTO;
import com.ss10.model.dto.response.TransactionCreditResponseDTO;

public interface TransactionCreditService {
    TransactionCreditResponseDTO spendWithCreditCard(TransactionCreditRequestDTO request);
    void generateMonthlySpendingReport();
}
