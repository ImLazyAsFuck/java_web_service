package com.ss8.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private String customerName;
    private String employeeName;
    private Double totalMoney;
    private LocalDateTime createdAt;
    private List<OrderDetailDTO> orderDetails;
}
