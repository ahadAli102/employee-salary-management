package com.ahad.salary.management.service.impl;

import com.ahad.salary.management.domain.entity.BankAccount;
import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.entity.Transaction;
import com.ahad.salary.management.domain.request.AddEmployeeRequest;
import com.ahad.salary.management.domain.request.ProvideSalaryRequest;
import com.ahad.salary.management.domain.request.UpdateEmployeeRequest;
import com.ahad.salary.management.domain.response.ProvideSalaryResponse;
import com.ahad.salary.management.repository.BankAccountRepository;
import com.ahad.salary.management.repository.BankRepository;
import com.ahad.salary.management.repository.EmployeeRepository;
import com.ahad.salary.management.domain.response.EmployeeResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.repository.TransactionRepository;
import com.ahad.salary.management.service.EmployeeService;
import com.ahad.salary.management.util.EmployeeUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TransactionRepository transactionRepository;

    @Value("${lowest.grade.rating}")
    private Integer lowestGradeRating;

    @Value("${lowest.salary}")
    private Integer lowestSalary;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public ResponseEntity<SingleResponse<Employee,String>> addEmployee(AddEmployeeRequest addEmployeeRequest) {
        System.out.println("add employee "+addEmployeeRequest);
        SingleResponse<Employee,String> response = new SingleResponse<>();
        if(addEmployeeRequest.getName() != null
                && addEmployeeRequest.getGrade() != null
                && addEmployeeRequest.getHouse() != null
                && addEmployeeRequest.getStreet() != null
                && addEmployeeRequest.getCity() != null
                && addEmployeeRequest.getState() != null
                && addEmployeeRequest.getCountry() != null
                && addEmployeeRequest.getMobile() != null
                && employeeRepository.findById(addEmployeeRequest.getId()).isEmpty()
                && (addEmployeeRequest.getGrade() >= 1 && addEmployeeRequest.getGrade() <= 6)

        ){
            try {
                Employee employee = employeeRepository.save(EmployeeUtils.ADD_EMPLOYEE_REQUEST_TO_EMPLOYEE_FUNCTION.apply(addEmployeeRequest));
                response.setData(employee);
                response.setStatusCode(HttpStatus.CREATED.value());

            } catch (Exception e) {
                response.setError("Some issue with internal server");
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError("Please enter all field properly");
        }
        return ResponseEntity
                .status(HttpStatus.valueOf(response.getStatusCode()))
                .body(response);
    }

    @Override
    public ResponseEntity<ListResponse<EmployeeResponse, String>> getAllEmployees() {
        List<EmployeeResponse> employeeResponseList = employeeRepository.getAllEmployeesWithoutBankInfo();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ListResponse<>(
                                HttpStatus.OK.value(),
                                employeeResponseList,
                                employeeResponseList.size(),
                                1,
                                null)
                );
    }

    @Override
    public ResponseEntity<SingleResponse<EmployeeResponse, String>> updateEmployee(int id, UpdateEmployeeRequest updateEmployeeRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            if (updateEmployeeRequest.getName() != null) {
                employee.setName(updateEmployeeRequest.getName());
            }
            if (updateEmployeeRequest.getGrade() != null && (updateEmployeeRequest.getGrade() >=1 && updateEmployeeRequest.getGrade() <=6)) {
                employee.setGrade(updateEmployeeRequest.getGrade());
            }
            if (updateEmployeeRequest.getHouse() != null) {
                employee.getAddress().setHouse(updateEmployeeRequest.getHouse());
            }
            if (updateEmployeeRequest.getCity() != null) {
                employee.getAddress().setCity(updateEmployeeRequest.getCity());
            }
            if (updateEmployeeRequest.getState() != null) {
                employee.getAddress().setState(updateEmployeeRequest.getState());
            }
            if (updateEmployeeRequest.getCountry() != null) {
                employee.getAddress().setCountry(updateEmployeeRequest.getCountry());
            }
            if (updateEmployeeRequest.getMobile() != null) {
                employee.setMobile(updateEmployeeRequest.getMobile());
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    EmployeeUtils.EMPLOYEE_TO_EMPLOYEE_RESPONSE_FUNCTION.apply(employee),
                                    null
                            )
                    );
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.NOT_FOUND.value(),
                                    null,
                                    "Please enter id correctly")
                    );
        }
    }

    @Override
    public ResponseEntity<SingleResponse<EmployeeResponse, String>> getEmployee(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    EmployeeUtils.EMPLOYEE_TO_EMPLOYEE_RESPONSE_FUNCTION.apply(employee),
                                    null
                            )
                    );
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.NOT_FOUND.value(),
                                    null,
                                    "Please enter id correctly")
                    );
        }
    }

    @Override
    public ResponseEntity<SingleResponse<String, String>> deleteEmployee(int id) {
        SingleResponse<String, String> response = new SingleResponse<>();
        if (employeeRepository.findById(id).isPresent()){
            employeeRepository.deleteById(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setData("Employee deleted");
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("No employee exists with the id " + id);
        }
        return ResponseEntity
                .status(HttpStatusCode.valueOf(response.getStatusCode()))
                .body(response);
    }

    @Override
    public ResponseEntity<SingleResponse<Double, String>> getTotalSalaryAmount(double lowestGradeSalary) {

        Optional<Double> totalAmount = employeeRepository
                .findAll()
                .stream()
                .map(employee -> lowestGradeSalary + (lowestSalary * (employee.getGrade() - lowestGradeRating)))
                .reduce(Double::sum);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new SingleResponse<>(
                                HttpStatus.OK.value(),
                                totalAmount.isEmpty() ? 0 : totalAmount.get(),
                                totalAmount.isEmpty() ? "No value present" : null
                        )
                );
    }

    @Override
    public ResponseEntity<SingleResponse<ProvideSalaryResponse, String>> provideSalary(
            ProvideSalaryRequest provideSalaryRequest
    ) {

        double totalAmount = Objects.requireNonNull(getTotalSalaryAmount(provideSalaryRequest.getLowerSalary()).getBody()).getData();
        System.out.println(provideSalaryRequest);
        System.out.println(totalAmount+"-"+provideSalaryRequest.getTotalSalary());

        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(provideSalaryRequest.getBankAccountId());

        if ( totalAmount <= provideSalaryRequest.getTotalSalary()
                && optionalBankAccount.isPresent()
                && totalAmount <= optionalBankAccount.get().getCurrentBalance()
        ) {
            BankAccount companyBankAccount = optionalBankAccount.get();
            companyBankAccount.setCurrentBalance(companyBankAccount.getCurrentBalance() - totalAmount);
            employeeRepository
                    .findAll()
                    .forEach( e-> {
                        double salary = provideSalaryRequest.getLowerSalary() + (lowestSalary * (e.getGrade() - lowestGradeRating));
                        e.getBankAccount().setCurrentBalance(
                                e.getBankAccount().getCurrentBalance() + salary
                        );
                        transactionRepository
                            .save(
                                    new Transaction(
                                            null,
                                            salary,
                                            EmployeeUtils.TransactionType.IN.toString(),
                                            LocalDateTime.now(),
                                            e.getBankAccount()
                                    )
                            );
                    });
            transactionRepository.save(
                    new Transaction(
                            null,
                            totalAmount,
                            EmployeeUtils.TransactionType.OUT.toString(),
                            LocalDateTime.now(),
                            companyBankAccount
                    )
            );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    new ProvideSalaryResponse(
                                            0,
                                            "Salary provided"
                                    ),
                                    null
                            )
                    );
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    new ProvideSalaryResponse(
                                            totalAmount - provideSalaryRequest.getTotalSalary(),
                                            "Please add more money"
                                    ),
                                    "Amount not sufficient"
                            )
                    );
        }
    }
}
