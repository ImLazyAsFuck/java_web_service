package com.ss6.service.order;

import com.ss6.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService{
    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByDate(LocalDate date);
}
