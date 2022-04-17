package com.deo.microservices.securityservice;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SimpleInMemorySecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails sam = User.withDefaultPasswordEncoder()
                .username("sam")
                .password("1234")
                .roles("USER")
                .build();
        UserDetails frodo = User.withDefaultPasswordEncoder()
                .username("frodo")
                .password("1111")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(sam, frodo);
    }
}
