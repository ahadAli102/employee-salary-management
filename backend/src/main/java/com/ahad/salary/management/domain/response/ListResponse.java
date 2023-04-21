package com.ahad.salary.management.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse<T,E> {

    private int statusCode;
    private List<T> data;
    private int pageSize;
    private int totalPages;
    private E error;
}
