package com.ahad.salary.management.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankResponse {
    private Integer id;
    private String name;
    private String address;
    private String country;
}
