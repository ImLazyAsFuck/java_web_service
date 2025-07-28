package com.babotea.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<OrderItemRequest> items;
}