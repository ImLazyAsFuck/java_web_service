package com.ss3.model.controller;

import com.ss3.model.dto.EmployeeDTO;
import com.ss3.model.entity.Employee;
import com.ss3.model.entity.EmployeeMapper;
import com.ss3.model.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public String employees(@RequestParam(required = false) String phone,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "phone") String sort,
                            Model model) {

        Sort.Direction direction = Sort.Direction.ASC;
        String sortBy = sort;

        if (sort.contains(",")) {
            String[] parts = sort.split(",");
            sortBy = parts[0];
            if (parts.length > 1 && "desc".equalsIgnoreCase(parts[1])) {
                direction = Sort.Direction.DESC;
            }
        }

        if (!"phone".equals(sortBy)) {
            sortBy = "phone";
        }

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(direction, sortBy));

        Page<Employee> result = (phone == null || phone.isBlank())
                ? employeeService.findAll(pageable)
                : employeeService.findByPhoneContaining(phone, pageable);

        model.addAttribute("employees", result);
        model.addAttribute("sort", sort);
        model.addAttribute("page", page);
        model.addAttribute("phone", phone);
        return "employee/list";
    }


    @GetMapping("/add")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "employee/add";
    }

    @PostMapping("/add")
    public String createEmployee(@ModelAttribute("employee") @Valid EmployeeDTO dto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "employee/add";
        }

        try {
            Employee entity = employeeMapper.toEntity(dto);
            employeeService.save(entity);
            return "redirect:/employee";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "employee/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        try {
            Employee employee = employeeService.findById(id);
            model.addAttribute("employee", employeeMapper.toDto(employee));
            return "employee/edit";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Không tìm thấy nhân viên với ID " + id);
            return "redirect:/employee";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @ModelAttribute("employee") @Valid EmployeeDTO dto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "employee/edit";
        }

        try {
            Employee entity = employeeMapper.toEntity(dto);
            entity.setId(id);
            employeeService.save(entity);
            return "redirect:/employee";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "employee/edit";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteById(id);
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/employee";
    }
}

