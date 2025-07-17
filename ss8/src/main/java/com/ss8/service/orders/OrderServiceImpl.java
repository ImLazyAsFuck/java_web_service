package com.ss8.service.orders;

import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.dto.OrderDetailDTO;
import com.ss8.model.dto.OrderRequestDTO;
import com.ss8.model.dto.OrderResponseDTO;
import com.ss8.model.entity.*;
import com.ss8.repo.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;
    private final DishRepo dishRepo;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy khách hàng"));
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy nhân viên"));

        double totalMoney = dto.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getPriceBuy() * detail.getQuantity())
                .sum();

        Orders order = Orders.builder()
                .customer(customer)
                .employee(employee)
                .totalMoney(totalMoney)
                .createdAt(LocalDateTime.now())
                .build();

        Orders savedOrder = orderRepo.save(order);

        List<OrderDetail> details = dto.getOrderDetails().stream().map(d -> {
            Dish dish = dishRepo.findById(d.getDishId())
                    .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy món ăn"));
            return OrderDetail.builder()
                    .order(savedOrder)
                    .dish(dish)
                    .quantity(d.getQuantity())
                    .priceBuy(d.getPriceBuy())
                    .build();
        }).collect(Collectors.toList());

        orderDetailRepo.saveAll(details);

        return toDTO(savedOrder, details);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        return orders.stream().map(order -> {
            List<OrderDetail> details = orderDetailRepo.findAll().stream()
                    .filter(d -> d.getOrder().getId().equals(order.getId()))
                    .collect(Collectors.toList());
            return toDTO(order, details);
        }).collect(Collectors.toList());
    }

    private OrderResponseDTO toDTO(Orders order, List<OrderDetail> details) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomer().getFullName())
                .employeeName(order.getEmployee().getFullname())
                .totalMoney(order.getTotalMoney())
                .createdAt(order.getCreatedAt())
                .orderDetails(details.stream().map(d -> OrderDetailDTO.builder()
                        .dishId(d.getDish().getId())
                        .quantity(d.getQuantity())
                        .priceBuy(d.getPriceBuy())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
