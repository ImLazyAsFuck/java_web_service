package com.ss10.controller;

import com.ss10.model.entity.Notification;
import com.ss10.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID accountId) {
        return ResponseEntity.ok(notificationService.getByAccount(accountId));
    }
}