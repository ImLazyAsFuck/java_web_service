package com.ss7.repo;

import com.ss7.model.entity.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepo extends JpaRepository<Seed, Long>{
    @Query("SELECT SUM(s.quantity) FROM Seed s")
    int sumAllQuantities();
}
