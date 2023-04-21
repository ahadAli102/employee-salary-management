package com.ahad.salary.management.util;

import com.ahad.salary.management.domain.entity.Bank;
import com.ahad.salary.management.domain.request.AddBankRequest;
import com.ahad.salary.management.domain.response.BankResponse;

import java.util.function.Function;

public final class BankUtils {

    public static final Function<Bank, BankResponse> BANK_TO_BANK_RESPONSE
            = bank -> new BankResponse(bank.getId(), bank.getName(), bank.getAddress(), bank.getCountry());

    public static final Function<AddBankRequest, Bank> BANK_REQUEST_TO_BANK_
            = addBankRequest -> new Bank(addBankRequest.getId(), addBankRequest.getName(), addBankRequest.getAddress(), addBankRequest.getCountry(),null);
}
