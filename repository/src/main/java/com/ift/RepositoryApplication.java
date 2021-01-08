package com.ift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liufei
 * @date 2021/1/6 11:47
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }
}
