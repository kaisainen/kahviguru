package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.example.nkk.models.Account;
import com.example.nkk.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String list(Model model) {
        model.addAttribute("accounts", accountService.listaaAccounts());
        return "admin";
    }

    @PostMapping("/admin")
    public String add(@RequestParam String username, @RequestParam String password) {
        if (accountService.findUserbyName(username) != null) {
            return "redirect:/admin";
        }

        // Account a = new Account(username, password, new ArrayList<>());
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(passwordEncoder.encode(password));
        a.setAuthorities(Arrays.asList("ADMIN"));
        // a.getAuthorities().add(e)
        accountService.addUser(a);
        return "redirect:/admin";
    }

    // @PostConstruct
    // public void init() {
    // Account admin = new Account("adminuser", passwordEncoder.encode("hervanta"),
    // new ArrayList<>());
    // admin.getAuthorities().add("ADMIN");
    // admin.getAuthorities().add("USER");
    // admin.getAuthorities().add("VIP");
    // accountService.addUser(admin);
    // }

}
