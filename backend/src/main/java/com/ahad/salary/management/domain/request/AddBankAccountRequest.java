package com.ahad.salary.management.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBankAccountRequest {
    private int accountNumber;
    private String accountName;
    private String accountType;
    private String branchName;
    private int employeeId;
    private int bankId;
}
