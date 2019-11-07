package com.ezblog.auth.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author dpc
 */
@RestController
@RequestMapping(value = "/oauth/token")
public class TokenRevokeEndpoint {
    @Autowired
    @Qualifier(value = "defaultTokenServices")
    private ConsumerTokenServices tokenServices;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @PostMapping(value = "/revoke")
    public ResponseEntity<Boolean> revokeToken(@RequestParam("tokenId") String tokenId, @RequestParam("userName") String userName) {
        boolean result = tokenServices.revokeToken(tokenId);
        Map<String, ? extends Session> map = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userName);
        map.entrySet().forEach(
                entry -> {
                    String sessionId = entry.getKey();
                    stringRedisTemplate.delete("spring:session:sessions:".concat(sessionId));
                    stringRedisTemplate.delete("spring:session:sessions:expires".concat(sessionId));
                    stringRedisTemplate.delete("spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:".concat(userName));
                }
        );
        return ResponseEntity.ok(result);
    }
}
