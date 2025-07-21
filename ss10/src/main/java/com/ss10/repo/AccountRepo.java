package com.ss10.repo;

import com.ss10.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID>{
    Optional<Account> findByCccd(String cccd);
}
