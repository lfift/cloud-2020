package com.ift.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liufei
 * @date 2021/1/6 11:48
 */
@RestController
public class RepositoryController {

    @GetMapping("/update")
    public Map<String, Object> update() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("message", "操作成功！");
        result.put("data", new Random().nextInt(100));
        return result;
    }
}
