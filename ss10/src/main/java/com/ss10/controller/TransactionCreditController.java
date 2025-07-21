package com.ss10.controller;

import com.ss10.model.dto.request.TransactionCreditRequestDTO;
import com.ss10.model.dto.response.APIResponse;
import com.ss10.model.dto.response.TransactionCreditResponseDTO;
import com.ss10.service.transactioncredit.TransactionCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-transactions")
@RequiredArgsConstructor
public class TransactionCreditController {

    private final TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<APIResponse<TransactionCreditResponseDTO>> createTransaction(
            @RequestBody @Validated TransactionCreditRequestDTO dto) {
        var response = transactionCreditService.spendWithCreditCard(dto);
        return new ResponseEntity<>(
                new APIResponse<>(response, "Quẹt thẻ thành công", 201),
                HttpStatus.CREATED
        );
    }
}
