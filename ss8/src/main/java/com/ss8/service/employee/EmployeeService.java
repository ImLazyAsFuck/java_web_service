package com.ss8.service.employee;

import com.ss8.model.dto.EmployeeDTO;
import com.ss8.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(EmployeeDTO dto);
    Employee update(Long id, EmployeeDTO dto);
    void delete(Long id);
    List<Employee> findAll();
}
