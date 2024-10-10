package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }


    public Account postNewUser(Account account){

        if(account.getUsername().isBlank() || account.getPassword().length() < 4)
        {
            return null;
        }
        else if(accountRepo.userExist(account.getUsername()) != null)
        {
            return null;
        }

        return accountRepo.save(account);
    }

    public Account postUserLogin(Account account){
        String dbPass = accountRepo.userPassCorrect(account.getUsername());
        if(accountRepo.userExist(account.getUsername()) != null && dbPass.equals(account.getPassword())){
            return accountRepo.userExist(account.getUsername());
        }
        return null;
    }
}
