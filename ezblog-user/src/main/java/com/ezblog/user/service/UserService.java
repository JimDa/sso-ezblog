package com.ezblog.user.service;

import domain.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User queryUserByName(String name);

    ResponseEntity<String> register(User registerInfo);

    List<User> queryAll();

    User queryUserByPhoneNum(String principal);

    User addUserByPhoneNum(User user);

    User queryUserById(Integer userId);
}
