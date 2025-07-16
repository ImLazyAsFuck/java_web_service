package com.ss7.repo;

import com.ss7.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentSlipRepo extends JpaRepository<PaymentSlip, Long>{
    @Query("SELECT COALESCE(SUM(p.amount), 0.0) FROM PaymentSlip p WHERE YEAR(p.createdAt) = ?1 AND MONTH(p.createdAt) = ?2")
    double sumAmountByMonth(int year, int month);
}
