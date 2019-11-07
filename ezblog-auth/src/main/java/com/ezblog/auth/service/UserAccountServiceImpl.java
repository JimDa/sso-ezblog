package com.ezblog.auth.service;

import com.ezblog.auth.mapper.UserAccountMapper;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.IpUtil;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IpUtil.getIp(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        User user = userAccountMapper.selectByUsername(name);
        if (null == user) {
            throw new UsernameNotFoundException(name);
        }
        return user;
    }

    @Override
    public User loadUserByLoginType(String loginType, String principal) {
        return userAccountMapper.selectByLoginType(loginType.equals("verify_code") ? "temp.phone_num" : "temp.".concat(loginType), principal);
    }

    @Override
    public User loadUserById(String userId) {
        return userAccountMapper.selectById(userId);
    }
}
