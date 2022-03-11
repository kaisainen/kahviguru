package com.example.nkk.repositories;

import com.example.nkk.models.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

}
