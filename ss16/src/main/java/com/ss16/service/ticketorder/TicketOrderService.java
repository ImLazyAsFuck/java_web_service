package com.ss16.service.ticketorder;

import com.ss16.model.dto.TicketOrderRequestDTO;
import com.ss16.model.dto.TicketOrderResponseDTO;

import java.util.List;

public interface TicketOrderService {
    TicketOrderResponseDTO placeOrder(TicketOrderRequestDTO dto);
    List<TicketOrderResponseDTO> getMyOrders();
    List<TicketOrderResponseDTO> getAllOrders();
}
