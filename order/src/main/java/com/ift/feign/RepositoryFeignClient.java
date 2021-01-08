package com.ift.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author liufei
 * @date 2021/1/6 11:37
 */
@FeignClient(name = "repository", fallback = RepositoryFeignClientFallback.class)
public interface RepositoryFeignClient {

    @GetMapping("/update")
    Map<String, Object> update();
}
