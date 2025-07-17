package com.ss8.service.customer;

import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.dto.CustomerDTO;
import com.ss8.model.entity.Customer;
import com.ss8.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer create(CustomerDTO dto) {
        Customer customer = Customer.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .numberOfPayments(dto.getNumberOfPayments())
                .status(true)
                .createdAt(LocalDateTime.now())
                .build();
        return customerRepo.save(customer);
    }

    @Override
    public Customer update(Long id, CustomerDTO dto) {
        Customer existing = customerRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Customer not found with ID: " + id));
        if (!existing.isStatus()) {
            throw new NoResourceFoundException("Customer has been deleted.");
        }

        existing.setFullName(dto.getFullName());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setNumberOfPayments(dto.getNumberOfPayments());

        return customerRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Customer not found with ID: " + id));
        customer.setStatus(false);
        customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepo.findByStatusTrue();
    }
}
