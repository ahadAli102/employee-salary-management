package com.ahad.salary.management.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBankRequest {
    private Integer id;
    private String name;
    private String address;
    private String country;
}
