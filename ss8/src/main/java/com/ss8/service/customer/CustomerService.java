package com.ss8.service.customer;

import com.ss8.model.dto.CustomerDTO;
import com.ss8.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerDTO dto);
    Customer update(Long id, CustomerDTO dto);
    void delete(Long id);
    List<Customer> getAll();
}
