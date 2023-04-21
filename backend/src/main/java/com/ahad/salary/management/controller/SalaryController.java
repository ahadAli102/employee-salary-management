package com.ahad.salary.management.controller;

import com.ahad.salary.management.response.SingleResponse;
import com.ahad.salary.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url.salary}")
@RequiredArgsConstructor
public class SalaryController {
    private final EmployeeService employeeService;

    @GetMapping("total-amount")
    public ResponseEntity<SingleResponse<Double, String>> getTotalSalaryAmount(@RequestParam double baseSalary) {
        return employeeService.getTotalSalaryAmount(baseSalary);
    }
}
