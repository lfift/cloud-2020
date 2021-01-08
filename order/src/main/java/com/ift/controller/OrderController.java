package com.ift.controller;

import com.ift.feign.RepositoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liufei
 * @date 2021/1/6 11:31
 */
@RestController
public class OrderController {

    @Autowired
    private RepositoryFeignClient repositoryFeignClient;

    @GetMapping("/saveOrder")
    public Map<String, Object> saveOrder() {
        Map<String, Object> update = repositoryFeignClient.update();
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put("code", 200);
        result.put("message", "操作成功！");
        result.put("data", update);
        return result;
    }
}
