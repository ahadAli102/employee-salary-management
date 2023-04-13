package com.ahad.salary.management.controller;

import com.ahad.salary.management.domain.request.AddBankRequest;
import com.ahad.salary.management.domain.request.UpdateBankRequest;
import com.ahad.salary.management.response.BankResponse;
import com.ahad.salary.management.response.ListResponse;
import com.ahad.salary.management.response.SingleResponse;
import com.ahad.salary.management.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base.url.bank}")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PutMapping
    public ResponseEntity<SingleResponse<BankResponse,String>> addBank(@RequestBody AddBankRequest addBankRequest){
        return bankService.addBank(addBankRequest);
    }

    @GetMapping
    public ResponseEntity<ListResponse<BankResponse,String>> getAllBankInformation(){
        return bankService.getAllBankInformation();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<BankResponse,String>> getBankInformation(@PathVariable("id") int id){
        return bankService.getBankInformation(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SingleResponse<BankResponse,String>> updateBankInformation(
            @PathVariable("id") int id,
            @RequestBody UpdateBankRequest updateBankRequest
    ){
        return bankService.getUpdateInformation(id, updateBankRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SingleResponse<String,String>> deleteBankInformation(@PathVariable("id") int id){
        return bankService.deleteBankInformation(id);
    }

}
