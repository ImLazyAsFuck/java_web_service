package com.ss16.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketOrderResponseDTO {
    private Long id;
    private Integer quantityTicket;
    private List<String> comboNames;
    private BigDecimal totalMoney;
    private LocalDateTime createdAt;
}
