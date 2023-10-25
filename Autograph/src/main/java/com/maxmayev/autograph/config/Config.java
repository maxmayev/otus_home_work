package com.maxmayev.autograph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("maxmayev");
    }
}
