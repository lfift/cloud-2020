package com.ift.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2021/1/11 17:25
 */
@RestController
public class ConfigController {

    @Value("${environment}")
    private String environment;

    @GetMapping("/testConfig")
    public String testConfig() {
        return environment;
    }
}
