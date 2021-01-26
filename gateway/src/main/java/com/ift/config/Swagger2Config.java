package com.ift.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置
 *
 * @author liufei
 * @date 2021/1/26 15:02
 */
@Component
public class Swagger2Config implements SwaggerResourcesProvider {
    /**
     * swagger2默认的url后缀
     */
    private static final String API_URI = "/v2/api-docs";

    /**
     * 网关路由
     */
    private final RouteLocator routeLocator;

    @Autowired
    private GatewayProperties gatewayProperties;

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String self;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public Swagger2Config(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> services = discoveryClient.getServices();
//        services.forEach(System.out::println);
//        services.forEach(service -> resources.add(buildSwaggerResource(service)));
//        return resources;
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        List<RouteDefinition> routes = gatewayProperties.getRoutes();

        for(RouteDefinition routeDefinition:routes){
            List<PredicateDefinition> predicates = routeDefinition.getPredicates();
            for(PredicateDefinition predicateDefinition:predicates){
                if("path".equalsIgnoreCase(predicateDefinition.getName())){
                    String s = predicateDefinition.getArgs()
                            .get(NameUtils.GENERATED_NAME_PREFIX + "0");
                    SwaggerResource swaggerResource = new SwaggerResource();
                    swaggerResource.setName(routeDefinition.getId());
                    swaggerResource.setLocation(s.replace("/**", API_URI));
                    resources.add(swaggerResource);
                }
            }
        }
        this.routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
        routeHosts.forEach(instance -> resources.add(buildSwaggerResource(instance.toLowerCase())));
        return resources;
    }

    private SwaggerResource buildSwaggerResource(String name) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setSwaggerVersion("2.0");
        if (name.equalsIgnoreCase(self)) {
            swaggerResource.setUrl(API_URI);
        } else {
            swaggerResource.setUrl("/" + name + API_URI);
        }
        return swaggerResource;
    }
}
