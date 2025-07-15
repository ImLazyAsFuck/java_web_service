package com.ss6.service.employee;

import com.ss6.model.entity.Employee;
import com.ss6.repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepo employeeRepo;

    @Override
    public void deleteById(Long id){
        employeeRepo.deleteById(id);
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }

    @Override
    public Employee findById(Long id){
        return employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
    }

    @Override
    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    @Override
    public Employee update(Employee employee){
        Employee existingEmployee =findById(employee.getId());
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setSalary(employee.getSalary());
        return employeeRepo.save(employee);
    }
}
