package com.ss10.repo;

import com.ss10.model.entity.CreditCard;
import com.ss10.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccount(Account account);
    boolean existsByAccount(Account account);
}
