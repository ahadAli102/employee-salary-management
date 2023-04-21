package com.ahad.salary.management.repository;

import com.ahad.salary.management.domain.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer>{
}
