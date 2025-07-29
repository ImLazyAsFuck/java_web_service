package com.ss16.service.ticketorder;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.ss16.model.dto.TicketOrderRequestDTO;
import com.ss16.model.dto.TicketOrderResponseDTO;
import com.ss16.model.entity.Combo;
import com.ss16.model.entity.TicketOrder;
import com.ss16.model.entity.User;
import com.ss16.repository.ComboRepository;
import com.ss16.repository.TicketOrderRepository;
import com.ss16.repository.UserRepository;
import com.ss16.security.AuthUtils;
import com.ss16.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketOrderRepository ticketOrderRepo;
    private final ComboRepository comboRepo;
    private final UserRepository userRepo;
    private final AuthUtils authUtils;

    @Override
    public TicketOrderResponseDTO placeOrder(TicketOrderRequestDTO dto) {
        User user = authUtils.getCurrentUser();

        List<Combo> combos = comboRepo.findAllById(dto.getComboIds());
        BigDecimal ticketPrice = new BigDecimal("100000");
        BigDecimal comboTotal = combos.stream()
            .map(Combo::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalMoney = ticketPrice.multiply(BigDecimal.valueOf(dto.getQuantityTicket()))
                                .add(comboTotal);

        TicketOrder order = TicketOrder.builder()
                .user(user)
                .quantityTicket(dto.getQuantityTicket())
                .combos(combos)
                .totalMoney(totalMoney)
                .createdAt(LocalDateTime.now())
                .build();

        ticketOrderRepo.save(order);

        return toDTO(order);
    }

    @Override
    public List<TicketOrderResponseDTO> getMyOrders() {
        User user = authUtils.getCurrentUser();
        return ticketOrderRepo.findByUser(user).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<TicketOrderResponseDTO> getAllOrders() {
        return ticketOrderRepo.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    private TicketOrderResponseDTO toDTO(TicketOrder order) {
        return TicketOrderResponseDTO.builder()
                .id(order.getId())
                .quantityTicket(order.getQuantityTicket())
                .comboNames(order.getCombos().stream().map(Combo::getName).toList())
                .totalMoney(order.getTotalMoney())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
