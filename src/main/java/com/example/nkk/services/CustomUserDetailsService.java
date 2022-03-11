package com.example.nkk.services;

import java.util.ArrayList;
import java.util.List;

import com.example.nkk.models.Account;
import com.example.nkk.repositories.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;
    // private AccountService as;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        // Account account = as.findUserbyName(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user:" + username);
        }

        List<SimpleGrantedAuthority> authorizations = new ArrayList<>();
        for (String authority : account.getAuthorities()) {
            authorizations.add(new SimpleGrantedAuthority(authority));
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                authorizations);

    }
}
