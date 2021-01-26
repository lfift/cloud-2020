package com.ift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;

/**
 * @author liufei
 * @date 2021/1/26 16:57
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @Autowired
    private SwaggerResourcesProvider swaggerResources;

    @GetMapping(value = "/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(this.securityConfiguration)
                        .orElseGet(() -> SecurityConfigurationBuilder.builder().build()),
                HttpStatus.OK));
    }

    @GetMapping(value = "/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono
                .just(new ResponseEntity<>(
                        Optional.ofNullable(this.uiConfiguration).orElseGet(
                                () -> UiConfigurationBuilder.builder().build()),
                        HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources() {
        return Mono
                .just(new ResponseEntity<>(this.swaggerResources.get(), HttpStatus.OK));
    }
}
