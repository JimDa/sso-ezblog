package com.ezblog.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class BlackListException extends AuthenticationException {
    public BlackListException(String msg, Throwable t) {
        super(msg, t);
    }

    public BlackListException(String msg) {
        super(msg);
    }
}
