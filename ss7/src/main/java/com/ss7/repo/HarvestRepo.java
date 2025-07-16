package com.ss7.repo;

import com.ss7.model.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HarvestRepo extends JpaRepository<Harvest, Long>{
    @Query("SELECT COALESCE(SUM(h.totalMoney), 0.0) FROM Harvest h WHERE YEAR(h.createdAt) = ?1 AND MONTH(h.createdAt) = ?2")
    double sumTotalMoneyByMonth(int year, int month);
}
