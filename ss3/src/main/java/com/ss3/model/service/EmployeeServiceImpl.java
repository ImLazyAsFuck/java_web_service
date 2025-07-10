package com.ss3.model.service;

import com.ss3.model.entity.Employee;
import com.ss3.model.repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public long count() {
        return employeeRepo.count();
    }

    @Override
    public Employee save(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }

        if (!StringUtils.hasText(employee.getName())) {
            throw new IllegalArgumentException("Name must not be empty");
        }

        if (!StringUtils.hasText(employee.getPhone())) {
            throw new IllegalArgumentException("Phone must not be empty");
        }

        if (!StringUtils.hasText(employee.getEmail())) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        return employeeRepo.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        if (!employeeRepo.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete: Employee not found with ID: " + id);
        }

        employeeRepo.deleteById(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepo.existsById(id);
    }

    @Override
    public Page<Employee> findByPhoneContaining(String phone, Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable must not be null");
        }

        if (!StringUtils.hasText(phone)) {
            return employeeRepo.findAll(pageable);
        }

        return employeeRepo.findByPhoneContaining(phone, pageable);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable must not be null");
        }

        return employeeRepo.findAll(pageable);
    }
}
