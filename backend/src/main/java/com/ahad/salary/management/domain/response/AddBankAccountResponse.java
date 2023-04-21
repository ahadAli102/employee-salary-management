package com.ahad.salary.management.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBankAccountResponse {
    private int accountNumber;
    private String accountName;
    private String accountType;
    private String branchName;
}
