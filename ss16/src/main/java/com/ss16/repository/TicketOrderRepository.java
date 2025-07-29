package com.ss16.repository;

import com.ss16.model.dto.ReportDTO;
import com.ss16.model.entity.TicketOrder;
import com.ss16.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long>{
    List<TicketOrder> findByUser(User user);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM TicketOrder t " +
            "WHERE t.user.id = :userId " +
            "AND (:playAreaId IS NULL OR t.playArea.id = :playAreaId) " +
            "AND (:comboId IS NULL OR :comboId IN (SELECT c.id FROM t.combos c))")
    boolean existsByUserAndPlayAreaIdOrComboId(Long id, Long playAreaId, Long comboId);

    ReportDTO fetchRevenueBy(String type);

    ReportDTO fetchAttendanceBy(String type);

    ReportDTO fetchComboUsageBy(String type);
}
