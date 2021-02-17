package com.ift.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security相关配置
 *
 * @author 19870
 * @date 2021/2/17
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("sang")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("ADMIN")
//                .and()
//                .withUser("javaboy")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
    }
}
