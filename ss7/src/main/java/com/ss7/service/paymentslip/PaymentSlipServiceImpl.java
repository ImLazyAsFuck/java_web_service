package com.ss7.service.paymentslip;

import com.ss7.model.entity.PaymentSlip;
import com.ss7.repo.PaymentSlipRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSlipServiceImpl implements PaymentSlipService{

    private final PaymentSlipRepo paymentSlipRepo;

    @Override
    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip){
        return paymentSlipRepo.save(paymentSlip);
    }

    @Override
    public List<PaymentSlip> getAllPaymentSlips(){
        return paymentSlipRepo.findAll();
    }

    @Override
    public PaymentSlip getPaymentSlipById(Long id){
        return paymentSlipRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentSlip not found with id = " + id));
    }
}
