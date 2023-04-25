package com.ahad.salary.management.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyAccountSummery {
    private double totalPaidSalary;
    private double currentAmount;
}
