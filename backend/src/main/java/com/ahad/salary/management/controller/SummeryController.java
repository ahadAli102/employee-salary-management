package com.ahad.salary.management.controller;

import com.ahad.salary.management.domain.response.CompanyAccountSummery;
import com.ahad.salary.management.domain.response.SingleResponse;
import com.ahad.salary.management.service.SummeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url.summery}")
@RequiredArgsConstructor
public class SummeryController {

    private final SummeryService summeryService;
    @GetMapping("/company/{id}")
    public ResponseEntity<SingleResponse<CompanyAccountSummery,String>> getCompanyAccountSummery(
            @PathVariable("id") int id
    ){
        return summeryService.getCompanyAccountSummery(id);
    }
}
