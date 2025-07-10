package com.ss3.model.repo;

import com.ss3.model.dto.EmployeeDTO;
import com.ss3.model.dto.EmployeeInfo;
import com.ss3.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    Employee findByPhone(String phone);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    Iterable<Employee> findBySalaryGreaterThan(double salary);

    @Query("SELECT new com.ss3.model.dto.EmployeeDTO(e.id, e.name, e.email, e.phone, e.salary) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTO();

    @Query("SELECT e from Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();


    Page<Employee> findByPhoneContaining(String phone, Pageable pageable);
}
