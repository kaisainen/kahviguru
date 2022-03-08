package com.example.nkk.controllers;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.example.nkk.models.Account;
import com.example.nkk.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Account admin = new Account("adminuser", passwordEncoder.encode("hervanta"), new ArrayList<>());
        admin.getAuthorities().add("ADMIN");
        admin.getAuthorities().add("USER");
        admin.getAuthorities().add("VIP");
        accountService.addUser(admin);
    }

}
