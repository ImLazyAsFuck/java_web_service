package com.ss8.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PaymentSlipDTO {
    private Long id;
    private String title;
    private String description;
    private Double money;
    private LocalDateTime createdAt;
}
