package com.ezblog.auth.provider;

import com.ezblog.auth.exceptions.BlackListException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 自定义黑名单校验器
 */
@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {
    Set<String> blackList = new HashSet<String>();

    public CustomIpAuthenticationProvider() {
        blackList.add("11.11.11.11");
        blackList.add("12.12.12.12");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteIp = details.getRemoteAddress();
        if (!blackList.contains(remoteIp)) {
            throw new BlackListException("您的ip已被拉入黑名单！");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
