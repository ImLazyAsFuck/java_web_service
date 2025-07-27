package com.ss14.service.ticket;

import com.ss14.model.dto.request.TicketRequestDTO;
import com.ss14.model.dto.response.TicketResponseDTO;

import java.util.List;

public interface TicketService {
    TicketResponseDTO bookTicket(TicketRequestDTO dto, String username);
    List<TicketResponseDTO> getMyTickets(String username);
    List<TicketResponseDTO> getAllTickets();
}
