package com.ahad.salary.management.repository;

import com.ahad.salary.management.domain.entity.Address;
import com.ahad.salary.management.domain.entity.BankAccount;
import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT " +
            "new com.ahad.salary.management.response.EmployeeResponse(employee.id, employee.name, employee.grade, employee.address, employee.mobile) " +
            "from Employee as employee")
    List<EmployeeResponse> getAllEmployeesWithoutBankInfo();
}
