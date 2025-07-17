package com.ss8.model.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private Long customerId;
    private Long employeeId;
    private List<OrderDetailDTO> orderDetails;
}
