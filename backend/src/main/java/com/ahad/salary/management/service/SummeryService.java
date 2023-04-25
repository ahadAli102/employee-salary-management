package com.ahad.salary.management.service;

import com.ahad.salary.management.domain.response.CompanyAccountSummery;
import com.ahad.salary.management.domain.response.SingleResponse;
import org.springframework.http.ResponseEntity;

public interface SummeryService {
    ResponseEntity<SingleResponse<CompanyAccountSummery,String>> getCompanyAccountSummery(int id);
}
