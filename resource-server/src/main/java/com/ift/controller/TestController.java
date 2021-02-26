package com.ift.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author liufei
 * @date 2021/2/25 17:29
 */
@RequestMapping("/resource")
@RestController
public class TestController {

    @PreAuthorize("#oauth2.hasAnyScope('all') || hasAnyRole('ROLE_USER')")
    @GetMapping("/{resource}")
    public String resource(@PathVariable String resource) {

        return resource;
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

}
