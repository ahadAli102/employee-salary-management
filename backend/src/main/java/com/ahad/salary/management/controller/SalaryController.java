package com.ahad.salary.management.controller;

import com.ahad.salary.management.domain.request.ProvideSalaryRequest;
import com.ahad.salary.management.domain.response.ProvideSalaryResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base.url.salary}")
@RequiredArgsConstructor
public class SalaryController {
    private final EmployeeService employeeService;

    @GetMapping("total-amount")
    public ResponseEntity<SingleResponse<Double, String>> getTotalSalaryAmount(@RequestParam double baseSalary) {
        return employeeService.getTotalSalaryAmount(baseSalary);
    }

    @PostMapping("provide")
    public ResponseEntity<SingleResponse<ProvideSalaryResponse, String>> provideSalary(
            @RequestBody ProvideSalaryRequest provideSalaryRequest
    ) {
        return employeeService.provideSalary(provideSalaryRequest);
    }
}
