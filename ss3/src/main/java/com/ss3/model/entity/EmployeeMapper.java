package com.ss3.model.entity;

import com.ss3.model.dto.EmployeeDTO;
import com.ss3.model.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    public Employee toEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        if (dto.getId() != null) emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setPhone(dto.getPhone());
        emp.setSalary(dto.getSalary());
        return emp;
    }

}
