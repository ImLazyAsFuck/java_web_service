package com.ss10.model.mapper;

import com.ss10.model.dto.request.NotificationResponseDTO;
import com.ss10.model.entity.Account;
import com.ss10.model.entity.Notification;
import com.ss10.model.enums.NotificationStatus;
import com.ss10.model.dto.request.NotificationDTO;

import java.time.LocalDateTime;

public class NotificationMapper {

    public static NotificationResponseDTO toDTO(Notification notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .status(notification.getStatus())
                .createdTime(notification.getCreatedTime())
                .build();
    }

    public static Notification toEntity(NotificationDTO dto, Account account) {
        return Notification.builder()
                .account(account)
                .content(dto.getContent())
                .status(NotificationStatus.UNREAD)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
