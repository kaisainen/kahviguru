package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.example.nkk.models.UserInfo;
import com.example.nkk.services.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/admin")
    public String list(Model model) {
        model.addAttribute("accounts", userInfoService.listaaUserInfot());
        return "admin";
    }

    // @PostMapping("/admin")
    // public String add(@RequestParam String username, @RequestParam String
    // password) {
    // Account a = new Account();
    // a.setUsername(username);
    // a.setPassword(passwordEncoder.encode(password));
    // a.setAuthorities(Arrays.asList("ADMIN"));
    // // a.getAuthorities().add(e)
    // accountService.addUser(a);
    // return "redirect:/admin";
    // }

    // @PostConstruct
    // public void init() {
    // userInfoService.addUser("ad", "hervanta", Arrays.asList("ADMIN"));
    // userInfoService.addUser("erkki", "hervanta", Arrays.asList("USER"));
    // }

}
