package com.example.nkk.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.nkk.models.UserInfo;
import com.example.nkk.repositories.UserInfoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(String username, String password, List<String> roles) {
        // public void addUser(String username, String password, Boolean isAdmin) {
        UserInfo u = new UserInfo();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        // u.setIsADmin(isAdmin);
        u.setAuthorities(roles);
        userInfoRepo.save(u);
    }

    public UserInfo muokkaaUserInfo(UserInfo account) {
        return userInfoRepo.save(account);
    }

    public void poistaUserInfo(long id) {
        userInfoRepo.deleteById(id);
    }

    public UserInfo getUserById(Long id) {
        return userInfoRepo.getById(id);
    }

    public List<UserInfo> listaaUserInfot() {
        return userInfoRepo.findAll();
    }

    public UserInfo findUserInfobyName(String username) {
        return userInfoRepo.findByUsername(username);
    }

}
