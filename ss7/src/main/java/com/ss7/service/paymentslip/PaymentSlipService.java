package com.ss7.service.paymentslip;

import com.ss7.model.entity.PaymentSlip;

import java.util.List;

public interface PaymentSlipService{
    List<PaymentSlip> getAllPaymentSlips();
    PaymentSlip getPaymentSlipById(Long id);
    PaymentSlip addPaymentSlip(PaymentSlip paymentSlip);
}
