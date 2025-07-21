package com.ss10.model.dto.response;

import com.ss10.model.enums.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {
    private UUID id;
    private String content;
    private NotificationStatus status;
    private LocalDateTime createdTime;
}
