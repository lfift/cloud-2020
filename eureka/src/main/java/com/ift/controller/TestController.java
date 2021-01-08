package com.ift.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author liufei
 * @date 2021/1/6 10:05
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return UUID.randomUUID().toString();
    }
}
