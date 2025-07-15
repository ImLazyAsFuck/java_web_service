package com.ss6.service.employee;

import com.ss6.model.entity.Employee;

import java.util.List;

public interface EmployeeService{
    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void deleteById(Long id);
}
