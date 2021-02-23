package com.ift.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2021/1/11 11:04
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${environment}")
    private String environment;

    @PreAuthorize("#oauth2.hasScope('requiredScope') || hasAnyRole('123')")
    @ApiOperation("测试")
    @GetMapping("/test")
    public String testConfig() {
        return environment;
    }
}
