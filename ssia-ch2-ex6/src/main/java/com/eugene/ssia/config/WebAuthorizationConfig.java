package com.eugene.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebAuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .anyRequest().authenticated() // 모든 요청은 인증이 필요하다.
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}
