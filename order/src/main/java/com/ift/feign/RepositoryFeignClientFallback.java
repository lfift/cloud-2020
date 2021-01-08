package com.ift.feign;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liufei
 * @date 2021/1/7 11:44
 */
@Component
public class RepositoryFeignClientFallback implements RepositoryFeignClient {
    public Map<String, Object> update() {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("code", 500);
        result.put("message", "请稍后重试！");
        return result;
    }
}
