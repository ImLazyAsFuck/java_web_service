package com.ss10.repo;

import com.ss10.model.entity.Notification;
import com.ss10.model.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, UUID>{
    List<Notification> findByAccountId(UUID accountId);

    @Query("SELECT n FROM Notification n WHERE n.account.id = :accountId AND n.status = :notificationStatus")
    List<Notification> findByAccount_IdAndStatus(UUID accountId, NotificationStatus notificationStatus);
}
