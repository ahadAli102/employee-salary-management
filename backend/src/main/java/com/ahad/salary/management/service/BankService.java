package com.ahad.salary.management.service;

import com.ahad.salary.management.domain.request.AddBankRequest;
import com.ahad.salary.management.domain.request.UpdateBankRequest;
import com.ahad.salary.management.domain.response.BankResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import org.springframework.http.ResponseEntity;

public interface BankService {
    ResponseEntity<SingleResponse<BankResponse,String>> addBank(AddBankRequest bankRequest);

    ResponseEntity<ListResponse<BankResponse, String>> getAllBankInformation();
    ResponseEntity<SingleResponse<BankResponse, String>> getBankInformation(int id);

    ResponseEntity<SingleResponse<BankResponse, String>> getUpdateInformation(int id, UpdateBankRequest updateBankRequest);

    ResponseEntity<SingleResponse<String, String>> deleteBankInformation(int id);
}
