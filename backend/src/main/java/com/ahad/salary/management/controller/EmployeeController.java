package com.ahad.salary.management.controller;

import com.ahad.salary.management.domain.request.UpdateEmployeeRequest;
import com.ahad.salary.management.domain.response.EmployeeResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.request.AddEmployeeRequest;
import com.ahad.salary.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base.url.employee}")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping
    public ResponseEntity<SingleResponse<Employee,String>> addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest){
        return employeeService.addEmployee(addEmployeeRequest);
    }

    @GetMapping
    public ResponseEntity<ListResponse<EmployeeResponse,String>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<EmployeeResponse,String>> getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SingleResponse<EmployeeResponse,String>> updateEmployee(
            @PathVariable int id,
            @RequestBody UpdateEmployeeRequest updateEmployeeRequest
            )
    {
        return employeeService.updateEmployee(id, updateEmployeeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SingleResponse<String,String>> deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
