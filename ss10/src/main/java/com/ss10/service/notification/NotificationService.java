package com.ss10.service.notification;

import com.ss10.model.entity.Account;
import com.ss10.model.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    void sendNotification(Account account, String message);
    List<Notification> getByAccount(UUID accountId);
    void markAllAsRead(UUID accountId);
    void deleteNotification(UUID notificationId);
}
