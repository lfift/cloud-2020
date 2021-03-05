package com.ift.authorization.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 获取公钥
 *
 * @author liufei
 * @date 2021/3/5 17:09
 */
@RestController
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    @GetMapping("/rsa/publicKey")
    public Map<String, Object> publicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
