package com.ss6.service.order;

import com.ss6.model.entity.Order;
import com.ss6.repo.OrderRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;

    public Order createOrder(Order order) {
        if (order == null || order.getUser() == null || order.getTotalPrice() == null) {
            throw new IllegalArgumentException("Invalid order data");
        }
        order.setCreatedAt(LocalDate.now());
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrdersByDate(LocalDate date) {
        return orderRepo.findAllByCreatedAt(date);
    }
}
