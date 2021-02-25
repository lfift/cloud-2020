package com.ift.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * @author liufei
 * @date 2021/2/24 16:31
 */
@EnableWebFluxSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
}
