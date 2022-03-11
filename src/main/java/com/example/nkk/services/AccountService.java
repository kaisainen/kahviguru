package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.Account;
import com.example.nkk.repositories.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public void addUser(Account account) {
        accountRepo.save(account);
    }

    public Account muokkaaAccount(Account account) {
        return accountRepo.save(account);
    }

    public void poistaAccount(long id) {
        accountRepo.deleteById(id);
    }

    public Account getUserById(Long id) {
        return accountRepo.getById(id);
    }

    public List<Account> listaaAccounts() {
        return accountRepo.findAll();
    }

    public Account findUserbyName(String username) {
        return accountRepo.findByUsername(username);
    }

}
