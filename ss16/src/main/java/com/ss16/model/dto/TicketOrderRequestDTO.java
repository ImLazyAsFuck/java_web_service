package com.ss16.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketOrderRequestDTO {
    private Integer quantityTicket;
    private List<Long> comboIds;
}
