package com.babotea.service.order;

import com.babotea.model.dto.request.OrderItemRequest;
import com.babotea.model.entity.Order;
import com.babotea.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, List<OrderItemRequest> items);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
    void updateStatus(Long orderId, OrderStatus status);
}
