package com.ahad.salary.management.service.impl;

import com.ahad.salary.management.domain.entity.Bank;
import com.ahad.salary.management.domain.entity.Employee;
import com.ahad.salary.management.domain.request.AddBankAccountRequest;
import com.ahad.salary.management.domain.response.AddBankAccountResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.repository.BankAccountRepository;
import com.ahad.salary.management.repository.BankRepository;
import com.ahad.salary.management.repository.EmployeeRepository;
import com.ahad.salary.management.service.BankAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankRepository bankRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public ResponseEntity<SingleResponse<AddBankAccountResponse, String>> addBankAccount(AddBankAccountRequest bankAccountRequest) {

        if ( bankRepository.findById(bankAccountRequest.getBankId()).isPresent()
                && employeeRepository.findById(bankAccountRequest.getEmployeeId()).isPresent()
                && bankAccountRequest.getAccountName() != null
                && bankAccountRequest.getAccountType() != null
                && bankAccountRequest.getBranchName() != null
                && bankAccountRepository.findById(bankAccountRequest.getAccountNumber()).isEmpty()
        ) {

            Bank bank = bankRepository.findById(bankAccountRequest.getBankId()).get();
            Employee employee = employeeRepository.findById(bankAccountRequest.getEmployeeId()).get();

            com.ahad.salary.management.domain.entity.BankAccount bankAccount = new com.ahad.salary.management.domain.entity.BankAccount(
                    bankAccountRequest.getAccountNumber(),
                    bankAccountRequest.getAccountType(),
                    bankAccountRequest.getAccountName(),
                    0,
                    bankAccountRequest.getBranchName(),
                    employee,
                    bank,
                    null
            );

            bankAccountRepository.save(bankAccount);

            AddBankAccountResponse addBankAccountResponse = new AddBankAccountResponse(
                    bankAccount.getNumber(),
                    bankAccount.getAccountName(),
                    bankAccount.getAccountType(),
                    bankAccount.getBranchName()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.OK.value(),
                                    addBankAccountResponse,
                                    null
                            )
                    );
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new SingleResponse<>(
                                    HttpStatus.BAD_REQUEST.value(),
                                    null,
                                    "Please Enter all field properly"
                            )
                    );
        }
    }
}
