package com.ift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 网关
 *
 * @author liufei
 * @date 2021/1/6 11:11
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("route_path", r -> r.path("/zhouchen1998")
                        .uri("https://blog.csdn.net"))
                .route("route_path", r -> r.path("/ift662498")
                        .uri("https://blog.csdn.net"))
                .build();
    }
}
