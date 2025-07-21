package com.ss10.service.notification;

import com.ss10.model.entity.Account;
import com.ss10.model.entity.Notification;
import com.ss10.model.enums.NotificationStatus;
import com.ss10.repo.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;

    @Override
    public void sendNotification(Account account, String message) {
        Notification notification = new Notification();
        notification.setAccount(account);
        notification.setContent(message);
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setCreatedTime(LocalDateTime.now());

        notificationRepo.save(notification);
    }

    @Override
    public List<Notification> getByAccount(UUID accountId){
        return notificationRepo.findByAccountId(accountId);
    }

    @Override
    public void markAllAsRead(UUID accountId) {
        List<Notification> notifications = notificationRepo.findByAccount_IdAndStatus(accountId, NotificationStatus.UNREAD);
        for (Notification n : notifications) {
            n.setStatus(NotificationStatus.READ);
        }
        notificationRepo.saveAll(notifications);
    }


    @Override
    public void deleteNotification(UUID notificationId) {
        notificationRepo.deleteById(notificationId);
    }
}
