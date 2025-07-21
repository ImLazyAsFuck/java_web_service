package com.ss10.repo;

import com.ss10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionCreditRepo extends JpaRepository<TransactionCredit, UUID>{
}
