package com.ift.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author liufei
 * @date 2021/1/7 15:54
 */
@Component
public class RepositoryFeignClientFallbackFactory implements FallbackFactory<RepositoryFeignClientFallback> {

    public RepositoryFeignClientFallback create(Throwable cause) {
        return new RepositoryFeignClientFallback();
    }
}
