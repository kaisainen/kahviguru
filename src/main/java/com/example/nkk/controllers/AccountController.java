package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.example.nkk.models.UserInfo;
import com.example.nkk.repositories.UserInfoRepo;
import com.example.nkk.services.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    private UserInfoService userInfoService;

    // @GetMapping("/admin")
    // public String list(Model model) {
    // model.addAttribute("accounts", userInfoService.listaaUserInfot());
    // return "admin";
    // }

    @GetMapping("/adminpath")
    @ResponseBody
    public String admin() {
        return "Admin!";
    }

    // @PostConstruct
    // public void init() {
    // userInfoService.addUser("ad", "hervanta", Arrays.asList("ADMIN", "USER"));
    // userInfoService.addUser("erkki", "hervanta", Arrays.asList("USER"));
    // // userInfoService.addUser("erkki", "hervanta", true);
    // }

}
