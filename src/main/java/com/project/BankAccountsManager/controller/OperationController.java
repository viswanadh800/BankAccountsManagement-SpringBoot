package com.project.BankAccountsManager.controller;

import com.project.BankAccountsManager.model.Accounts;
import com.project.BankAccountsManager.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.random.RandomGenerator;

@RestController
@RequestMapping("operations")
public class OperationController {

    @Autowired
    OperationService operationService;

    @PutMapping("register")
    public ResponseEntity<String> register(@RequestBody Accounts account){
        System.out.println("register");
        return operationService.register(account);
    }

    @PostMapping("transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam double amt){
        return operationService.transfer(fromAccountNumber,toAccountNumber,amt);
    }

    @PostMapping("withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String accountNumber, @RequestParam double amt){
        return operationService.withdraw(accountNumber,amt);
    }

    @PostMapping("deposite")
    public ResponseEntity<String> deposite(@RequestParam String accountNumber, @RequestParam double amt){
        return operationService.deposite(accountNumber,amt);
    }

    @DeleteMapping("unregister")
    public ResponseEntity<String> unregister(@RequestParam String accountNumber){
        return operationService.unregister(accountNumber);
    }

    @GetMapping("view")
    public ResponseEntity<String> view(){
        return operationService.view();
    }
}
