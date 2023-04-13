package com.ahad.salary.management.util;

import com.ahad.salary.management.domain.entity.Address;
import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.request.AddEmployeeRequest;
import com.ahad.salary.management.response.EmployeeResponse;

import java.util.function.Function;

public final class EmployeeUtils {

    public static final Function<AddEmployeeRequest,Employee> ADD_EMPLOYEE_REQUEST_TO_EMPLOYEE_FUNCTION
            = addEmployeeRequest -> new Employee(
                    addEmployeeRequest.getId(),
                    addEmployeeRequest.getName(),
                    addEmployeeRequest.getGrade(),
                    new Address(
                            addEmployeeRequest.getHouse(),
                            addEmployeeRequest.getStreet(),
                            addEmployeeRequest.getCity(),
                            addEmployeeRequest.getState(),
                            addEmployeeRequest.getCountry()
                    ),
                    addEmployeeRequest.getMobile(),
                    null
            );

    public static final Function<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_FUNCTION
            = employee -> new EmployeeResponse(
                    employee.getId(),
                    employee.getName(),
                    employee.getGrade(),
                    employee.getAddress(),
                    employee.getMobile()
            );
}
