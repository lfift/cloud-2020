package com.ift.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 授权服务器
 *
 * @author 19870
 * @date 2021/2/17
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置端点安全策略，也就是谁能访问谁不能访问
     * #checkTokenAccess是资源服务校验Token有效性时的端点，此处配置为所有人都可以访问
     *
     * @param security 资源服务配置
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(new BCryptPasswordEncoder())
                .checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端信息，可通过clients.jdbc()配置存储在数据库中，此处配置为存储在内存中
     *
     * @param clients 客户端配置
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client_1")
                .secret(new BCryptPasswordEncoder().encode("123"))
                .scopes("all").authorizedGrantTypes("authorization_code", "refresh_token")
                .redirectUris("https://www.baidu.com");
    }

    /**
     * 端点配置
     * #allowedTokenEndpointRequestMethods配置访问获取Token端点的请求方式
     * #tokenServices配置Token存储位置以及相关属性信息比如：Token有效期限、RefreshToken有效期限
     * #authorizationCodeServices配置授权码存储位置
     *
     * @param endpoints 端点对象
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenServices(tokenServices())
                .authorizationCodeServices(authorizationCodeServices());
    }

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        //是否支持刷新Token
        tokenServices.setSupportRefreshToken(true);
        //access_token存储位置
        tokenServices.setTokenStore(new InMemoryTokenStore());
        //Token过期时间
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);
        //refresh_token的过期时间
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);
        return tokenServices;
    }

    /**
     * 授权码存储位置
     *
     * @return 授权码服务
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
