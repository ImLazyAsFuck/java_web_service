package com.ss8.controller;

import com.ss8.model.dto.EmployeeDTO;
import com.ss8.model.entity.Employee;
import com.ss8.model.dto.ApiResponse;
import com.ss8.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody EmployeeDTO dto) {
        Employee employee = employeeService.create(dto);
        return ResponseEntity.ok(
                ApiResponse.<Employee>builder()
                        .status(true)
                        .message("Employee created successfully")
                        .data(employee)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        Employee updated = employeeService.update(id, dto);
        return ResponseEntity.ok(
                ApiResponse.<Employee>builder()
                        .status(true)
                        .message("Employee updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .status(true)
                        .message("Employee deleted successfully")
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(
                ApiResponse.<List<Employee>>builder()
                        .status(true)
                        .message("Fetched all employees")
                        .data(employees)
                        .build()
        );
    }
}
