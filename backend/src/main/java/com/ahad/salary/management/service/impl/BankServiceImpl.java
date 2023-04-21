package com.ahad.salary.management.service.impl;

import com.ahad.salary.management.domain.entity.Bank;
import com.ahad.salary.management.domain.request.AddBankRequest;
import com.ahad.salary.management.domain.request.UpdateBankRequest;
import com.ahad.salary.management.repository.BankRepository;
import com.ahad.salary.management.domain.response.BankResponse;
import com.ahad.salary.management.domain.response.ListResponse;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.service.BankService;
import com.ahad.salary.management.util.BankUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public ResponseEntity<SingleResponse<BankResponse,String>> addBank(AddBankRequest bankRequest) {
        SingleResponse<BankResponse,String> response = new SingleResponse<>();
        if (bankRequest.getId() != null
                && bankRequest.getName() != null
                && bankRequest.getAddress() != null
                && bankRequest.getCountry() != null
                && bankRepository.findById(bankRequest.getId()).isEmpty()
        ) {
            try {
                Bank bank = BankUtils.BANK_REQUEST_TO_BANK_.apply(bankRequest);
                BankResponse bankResponse = BankUtils.BANK_TO_BANK_RESPONSE.apply(bankRepository.save(bank));
                response.setStatusCode(HttpStatus.CREATED.value());
                response.setData(bankResponse);
            } catch (Exception e) {
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setError("Internal Server Error");
            }
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError("Please enter all field");
        }
        return ResponseEntity
                .status(HttpStatusCode.valueOf(response.getStatusCode()))
                .body(response);
    }

    @Override
    public ResponseEntity<ListResponse<BankResponse, String>> getAllBankInformation() {
        List<BankResponse> bankResponses = bankRepository.getAllBankInformation();
        return ResponseEntity
                .ok(
                        new ListResponse<>(
                                HttpStatus.OK.value(),
                                bankResponses,
                                bankResponses.size(),
                                1,
                                null
                        )
                );
    }

    @Override
    public ResponseEntity<SingleResponse<BankResponse, String>> getBankInformation(int id) {
        BankResponse bankResponse = bankRepository.getBankInformation(id);
        SingleResponse<BankResponse, String> response = new SingleResponse<>();
        if(bankResponse != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(bankResponse);
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("Please enter actual bank id");
        }
        return ResponseEntity
                .status(HttpStatusCode.valueOf(response.getStatusCode()))
                .body(response);
    }

    @Override
    public ResponseEntity<SingleResponse<BankResponse, String>> getUpdateInformation(
            int id, UpdateBankRequest updateBankRequest
    ) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        SingleResponse<BankResponse, String> response = new SingleResponse<>();

        if(optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            if(updateBankRequest.getName() != null){
                bank.setName(updateBankRequest.getName());
            }
            if(updateBankRequest.getAddress() != null){
                bank.setAddress(updateBankRequest.getAddress());
            }
            if(updateBankRequest.getCountry() != null){
                bank.setCountry(updateBankRequest.getCountry());
            }
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(
                    new BankResponse(bank.getId(), bank.getName(), bank.getAddress(), bank.getCountry())
            );
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("No value exists with the id " + id);
        }
        return ResponseEntity
                .status(HttpStatus.valueOf(response.getStatusCode()))
                .body(response);
    }

    @Override
    public ResponseEntity<SingleResponse<String, String>> deleteBankInformation(int id) {
        SingleResponse<String, String> response = new SingleResponse<>();
        if (bankRepository.findById(id).isPresent()){
            bankRepository.deleteById(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setData("Bank deleted");
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("No value exists with the id " + id);
        }
        return ResponseEntity
                .status(HttpStatusCode.valueOf(response.getStatusCode()))
                .body(response);
    }
}
