package com.example.ems.controller;

import com.example.ems.entity.ConfirmationForm;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {
    private EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        List<Employee> employeeList = (List<Employee>) employeeRepo.findAll();
        model.addAttribute("employees", employeeList);
        model.addAttribute("employee", new Employee());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "index";
    }
}
