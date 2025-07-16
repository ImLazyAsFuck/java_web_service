package com.ss7.controller;

import com.ss7.model.dto.DataResponse;
import com.ss7.model.entity.PaymentSlip;
import com.ss7.service.paymentslip.PaymentSlipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentSlipController{

    private final PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<DataResponse<List<PaymentSlip>>> findAll(){
        return ResponseEntity.ok(new DataResponse<>(paymentSlipService.getAllPaymentSlips(), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<PaymentSlip>> findById(@PathVariable Long id){
        return ResponseEntity.ok(new DataResponse<>(paymentSlipService.getPaymentSlipById(id), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<PaymentSlip>> save(@RequestBody PaymentSlip paymentSlip){
        return ResponseEntity.ok(new DataResponse<>(paymentSlipService.addPaymentSlip(paymentSlip), HttpStatus.CREATED));
    }
}
