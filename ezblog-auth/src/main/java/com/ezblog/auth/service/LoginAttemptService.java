package com.ezblog.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 * @author dpc
 */
@Service
public class LoginAttemptService {
    public static final String LOGIN_FAILURE_RATE_LIMIT_KEY_PREFIX = "RATE_LIMIT:LOGIN_FAILURE:";
    public static final Integer MAX_LOGIN_FAILURE_NUM_PER_DAY = 10;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void loginSucceed(String key) {
        stringRedisTemplate.delete(LOGIN_FAILURE_RATE_LIMIT_KEY_PREFIX.concat(key));
    }

    public void loginFailed(String key) {
        String limitKey = LOGIN_FAILURE_RATE_LIMIT_KEY_PREFIX.concat(key);
        stringRedisTemplate.opsForValue().increment(limitKey);
        stringRedisTemplate.expireAt(limitKey, Date.from(LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toInstant()));
    }

    public boolean isBlocked(String key) {
        return Integer.valueOf(Optional.ofNullable(stringRedisTemplate.opsForValue().get(LOGIN_FAILURE_RATE_LIMIT_KEY_PREFIX.concat(key))).orElse("0")) > MAX_LOGIN_FAILURE_NUM_PER_DAY;
    }
}
