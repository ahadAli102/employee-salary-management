package com.ahad.salary.management.service;

import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.request.AddEmployeeRequest;
import com.ahad.salary.management.domain.request.ProvideSalaryRequest;
import com.ahad.salary.management.domain.request.UpdateEmployeeRequest;
import com.ahad.salary.management.domain.response.EmployeeResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.ProvideSalaryResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<SingleResponse<Employee,String>> addEmployee(AddEmployeeRequest addEmployeeRequest);

    ResponseEntity<ListResponse<EmployeeResponse, String>> getAllEmployees();

    ResponseEntity<SingleResponse<EmployeeResponse, String>> updateEmployee(int id, UpdateEmployeeRequest updateEmployeeRequest);

    ResponseEntity<SingleResponse<EmployeeResponse, String>> getEmployee(int id);

    ResponseEntity<SingleResponse<String, String>> deleteEmployee(int id);

    ResponseEntity<SingleResponse<Double,String>> getTotalSalaryAmount(double lowestGradeSalary);

    ResponseEntity<SingleResponse<ProvideSalaryResponse, String>> provideSalary(ProvideSalaryRequest provideSalaryRequest);
}
