package com.b910.service.order;

import com.b910.model.dto.request.CheckoutRequest;

public interface OrderService {
    void checkout(CheckoutRequest request);
}
