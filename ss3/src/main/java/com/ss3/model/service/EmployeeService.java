package com.ss3.model.service;

import com.ss3.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService{
    Employee save(Employee t);
    Employee findById(Long id);
    void deleteById(Long id);
    Iterable<Employee> findAll();
    long count();
    boolean existsById(Long id);
    Page<Employee> findByPhoneContaining(String phone, Pageable pageable);
    Page<Employee> findAll(Pageable pageable);

}
