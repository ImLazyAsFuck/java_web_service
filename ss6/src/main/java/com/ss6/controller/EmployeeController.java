package com.ss6.controller;

import com.ss6.model.dto.DataResponse;
import com.ss6.model.entity.Employee;
import com.ss6.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController{

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Employee>>> getAllEmployees(){
        return ResponseEntity.ok(new DataResponse<>(employeeService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Employee>> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(new DataResponse<>(employeeService.save(employee), HttpStatus.CREATED));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataResponse<Employee>> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(new DataResponse<>(employeeService.update(employee), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Employee>> getEmployeeById(Long id){
        return ResponseEntity.ok(new DataResponse<>(employeeService.findById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
