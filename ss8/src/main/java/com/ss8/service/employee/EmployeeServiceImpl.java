package com.ss8.service.employee;

import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.dto.EmployeeDTO;
import com.ss8.model.entity.Employee;
import com.ss8.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee create(EmployeeDTO dto) {
        Employee employee = Employee.builder()
                .fullname(dto.getFullname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .position(dto.getPosition())
                .salary(dto.getSalary())
                .build();
        return employeeRepo.save(employee);
    }

    @Override
    public Employee update(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Employee not found with ID: " + id));

        existing.setFullname(dto.getFullname());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());
        existing.setPosition(dto.getPosition());
        existing.setSalary(dto.getSalary());

        return employeeRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Employee not found with ID: " + id));
        employeeRepo.delete(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
}
