package com.ezblog.auth.service;

import domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserAccountService extends UserDetailsService {
//    User loadUserByPhoneNum(String phoneNum);

    User loadUserByLoginType(String loginType, String principal);
}
