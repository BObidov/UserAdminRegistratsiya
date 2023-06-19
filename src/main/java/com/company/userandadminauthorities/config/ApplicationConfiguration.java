package com.company.userandadminauthorities.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init() {
        System.out.println("Execute init method form ApplicationConfiguration classes!");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Execute destroy method form ApplicationConfiguration classes!");
    }



}
