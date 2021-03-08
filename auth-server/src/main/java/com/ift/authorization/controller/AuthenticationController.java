package com.ift.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 认证信息
 *
 * @author 19870
 * @date 2021/2/25
 */
@RestController
public class AuthenticationController {

    @GetMapping("/oauth/user_info")
    public Principal userInfo(Principal principal) {
        return principal;
    }
}
