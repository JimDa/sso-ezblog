package com.ezblog.auth.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping(value = "/oauth/token")
public class TokenRevokeEndpoint {
    @Resource(name = "defaultTokenServices")
    private ConsumerTokenServices tokenServices;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "/revoke")
    public ResponseEntity<Boolean> revokeToken(@RequestParam("tokenId") String tokenId, @RequestParam("userName") String userName) {
        boolean result = tokenServices.revokeToken(tokenId);
        final Set<Object> members = redisTemplate.boundSetOps("spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:".concat(userName)).members();

        return ResponseEntity.ok(result);
    }
}
