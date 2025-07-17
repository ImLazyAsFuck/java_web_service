package com.ss8.service.orders;

import com.ss8.model.dto.OrderRequestDTO;
import com.ss8.model.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getAllOrders();
}
