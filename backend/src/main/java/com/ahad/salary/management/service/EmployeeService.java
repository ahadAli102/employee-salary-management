package com.ahad.salary.management.service;

import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.request.AddEmployeeRequest;
import com.ahad.salary.management.domain.request.UpdateEmployeeRequest;
import com.ahad.salary.management.response.EmployeeResponse;
import com.ahad.salary.management.response.ListResponse;
import com.ahad.salary.management.response.SingleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EmployeeService {
    ResponseEntity<SingleResponse<Employee,String>> addEmployee(AddEmployeeRequest addEmployeeRequest);

    ResponseEntity<ListResponse<EmployeeResponse, String>> getAllEmployees();

    ResponseEntity<SingleResponse<EmployeeResponse, String>> updateEmployee(int id, UpdateEmployeeRequest updateEmployeeRequest);

    ResponseEntity<SingleResponse<EmployeeResponse, String>> getEmployee(int id);
}
