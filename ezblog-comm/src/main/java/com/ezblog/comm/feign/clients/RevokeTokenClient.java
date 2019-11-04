package com.ezblog.comm.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", url = "http://127.0.0.1:8081/auth-service", path = "/oauth", contextId = "revoke")
public interface RevokeTokenClient {
    @PostMapping(value = "/token/revoke")
    ResponseEntity<Boolean> revoke(@RequestParam("tokenId") String tokenId, @RequestParam("userName") String userName);
}
