package com.ahad.salary.management.repository;

import com.ahad.salary.management.domain.entity.Transaction;
import com.ahad.salary.management.util.EmployeeUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    String OUT = EmployeeUtils.TransactionType.OUT.toString();

    @Query("select sum (t.amount) " +
            "from Transaction as t " +
            "where t.type =  com.ahad.salary.management.repository.TransactionRepository.OUT " +
            "and t.account.number = :number")
    double getSumOfPaidSalaries(@Param("number") int number);
}
