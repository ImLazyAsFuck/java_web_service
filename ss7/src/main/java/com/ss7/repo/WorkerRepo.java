package com.ss7.repo;

import com.ss7.model.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkerRepo extends JpaRepository<Worker, Long>{
    @Query("SELECT COALESCE(SUM(w.salary), 0.0) FROM Worker w")
    double sumAllSalaries();
}
