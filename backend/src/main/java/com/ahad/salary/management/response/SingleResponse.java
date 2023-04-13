package com.ahad.salary.management.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleResponse <T,E>{
    private int statusCode;
    private T data;
    private E error;
}
