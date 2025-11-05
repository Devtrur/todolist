package com.todolist.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    private static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
    };

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth ->{
//            auth.requestMatchers("/todos/**").permitAll()
//                    .requestMatchers(PERMIT_ALL_LIST).permitAll();
//            auth.anyRequest().authenticated();
//        })
//                .addFilterBefore()
//
//        return http.build();
//    }
}
