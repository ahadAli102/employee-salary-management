package com.ahad.salary.management.service;

import com.ahad.salary.management.domain.request.AddBankAccountRequest;
import com.ahad.salary.management.domain.response.AddBankAccountResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import org.springframework.http.ResponseEntity;

public interface BankAccountService {

    ResponseEntity<SingleResponse<AddBankAccountResponse, String>> addBankAccount(AddBankAccountRequest bankAccountRequest);

}
