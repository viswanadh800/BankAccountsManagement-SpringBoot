package com.project.BankAccountsManager.repository;

import com.project.BankAccountsManager.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<Accounts,String> {
}
