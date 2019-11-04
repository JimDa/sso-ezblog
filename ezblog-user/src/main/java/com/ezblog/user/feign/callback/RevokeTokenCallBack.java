package com.ezblog.user.feign.callback;

import com.ezblog.user.feign.clients.RevokeTokenClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RevokeTokenCallBack implements RevokeTokenClient {
    @Override
    public ResponseEntity<Boolean> revoke(String tokenId, String userName) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE);
    }
}
