package com.project.BankAccountsManager.service;

import com.project.BankAccountsManager.model.Accounts;
import com.project.BankAccountsManager.repository.OperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    OperationRepo operationRepo;

    private boolean doesExists(String id){
    //    System.out.println("Checking for existance");
        return operationRepo.existsById(id);
    }

    public ResponseEntity<String> register(Accounts account) {
        System.out.println(account.getAccountNumber());
        if(!doesExists(account.getAccountNumber())){
        //    System.out.println(account.getAccountNumber());
            if(account.getMinBalance()> account.getAmount()){
                return new ResponseEntity<>("Cannot register because avaliable balance is greater than minimum balance requirement", HttpStatus.NOT_IMPLEMENTED);
            }
            operationRepo.save(account);
            return new ResponseEntity<>("Registered successfuly", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Account Number already exists",HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<String> transfer(String fromAccountNumber, String toAccountNumber, double amt) {
        if(doesExists(fromAccountNumber) && doesExists(toAccountNumber)){
            Accounts from = operationRepo.findById(fromAccountNumber).get();
            Accounts to = operationRepo.findById(toAccountNumber).get();
            if(from.getAmount()-amt>=from.getMinBalance()){
                from.setAmount(from.getAmount()-amt);
                to.setAmount(to.getAmount()+amt);
                return new ResponseEntity<>("Transferred sucessfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Unable to transfer due to minimum requirement of funds",HttpStatus.NOT_IMPLEMENTED);
            }
        }
        else if(!doesExists(fromAccountNumber)){
            return new ResponseEntity<>("Account number "+fromAccountNumber+" does not exists",HttpStatus.NOT_IMPLEMENTED);
        }
        else{
            return new ResponseEntity<>("Account number "+toAccountNumber+" does not exists",HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<String> withdraw(String accountNumber, double amt) {
        if(doesExists(accountNumber)){
            Accounts account = operationRepo.findById(accountNumber).get();
            if(account.getAmount()-amt>=account.getMinBalance()){
                account.setAmount(account.getAmount()-amt);
                return new ResponseEntity<>("Withdrawn sucessfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Unable to withdraw due to minimum requirement of funds",HttpStatus.NOT_IMPLEMENTED);
            }
        }
        else //if(!doesExists(AccountNumber)){
        {
            return new ResponseEntity<>("Account number " + accountNumber + " does not exists", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<String> deposite(String accountNumber, double amt) {
        if(doesExists(accountNumber)){
            Accounts account = operationRepo.findById(accountNumber).get();
            account.setAmount(account.getAmount()+amt);
            return new ResponseEntity<>("Deposited sucessfully",HttpStatus.OK);
        }
        else //if(!doesExists(AccountNumber)){
        {
            return new ResponseEntity<>("Account number " + accountNumber + " does not exists", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<String> unregister(String accountNumber) {
        if(doesExists(accountNumber)){
            operationRepo.deleteById(accountNumber);
            return new ResponseEntity<>("Account Number "+accountNumber+" has been unregistered sucessfully",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Account Number "+accountNumber+" does not exists",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> view() {
        List<Accounts> accounts = operationRepo.findAll();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++){
            sb.append(accounts.get(i).toString());
            sb.append('\n');
        }
        return new ResponseEntity<>(sb.toString(),HttpStatus.OK);
    }
}
