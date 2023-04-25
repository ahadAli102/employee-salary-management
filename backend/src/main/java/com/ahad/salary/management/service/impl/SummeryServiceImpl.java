package com.ahad.salary.management.service.impl;

import com.ahad.salary.management.domain.entity.BankAccount;
import com.ahad.salary.management.domain.response.CompanyAccountSummery;
import com.ahad.salary.management.domain.response.EmployeeSalarySheetResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.repository.BankAccountRepository;
import com.ahad.salary.management.repository.EmployeeRepository;
import com.ahad.salary.management.repository.TransactionRepository;
import com.ahad.salary.management.service.SummeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummeryServiceImpl implements SummeryService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<SingleResponse<CompanyAccountSummery, String>> getCompanyAccountSummery(int id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()){
            CompanyAccountSummery companyAccountSummery = new CompanyAccountSummery();
            companyAccountSummery.setCurrentAmount(bankAccount.get().getCurrentBalance());
            companyAccountSummery.setTotalPaidSalary(transactionRepository.getSumOfPaidSalaries(id));
            return ResponseEntity
                    .ok(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    companyAccountSummery,
                                    null
                            )
                    );
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.BAD_REQUEST.value(),
                                    null,
                                    "Please enter proper value for company id"
                            )
                    );
        }

    }

    @Override
    public ResponseEntity<ListResponse<EmployeeSalarySheetResponse, String>> getSalarySheet() {
        List<EmployeeSalarySheetResponse> employeeSalarySheetResponses =
                employeeRepository.findAll()
                .stream().map(employee -> {

                    EmployeeSalarySheetResponse essr = new EmployeeSalarySheetResponse();
                    essr.setName(employee.getName());
                    essr.setRank(employee.getGrade());
                    essr.setSalary(transactionRepository.getLastTransactionOfEmployee(employee.getId()));
                    System.out.println(essr);
                    return essr;

                }).toList();

        return ResponseEntity
                .ok(
                        new ListResponse<>(
                                HttpStatus.OK.value(),
                                employeeSalarySheetResponses,
                                employeeSalarySheetResponses.size(),
                                1,
                                null
                        )
                );
    }
}
