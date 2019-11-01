package com.ezblog.user.service;

import dto.AliSmsResponse;

public interface IAlismsService {
    AliSmsResponse sendMessage(String phoneNum);
}
