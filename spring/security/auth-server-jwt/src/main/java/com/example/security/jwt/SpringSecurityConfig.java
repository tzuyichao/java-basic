package com.example.security.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        log.info("create AuthenticationManager");
        return super.authenticationManagerBean();
    }
}
