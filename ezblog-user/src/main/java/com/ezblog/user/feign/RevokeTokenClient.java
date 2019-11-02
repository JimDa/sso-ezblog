package com.ezblog.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", url = "http://127.0.0.1:8081/auth-service", path = "/oauth", contextId = "revoke")
public interface RevokeTokenClient {
    @PostMapping(value = "/token/revoke-by-id/{tokenId}")
    ResponseEntity<Boolean> revoke(@PathVariable("tokenId") String tokenId);
}
