package com.rf.coin_analysis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
* login gereltiren apiler
* api/v1/favorite/**
* api/v1/user/delete/*
* */
@Configuration
@EnableWebSecurity
public class WebSecurity {
    private final Filter filter;

    public WebSecurity(Filter filter) {
        this.filter = filter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((x)->
                x.requestMatchers("/api/v1/favorite/**").authenticated()
                        .requestMatchers("api/v1/user/delete/*").authenticated()
                        .anyRequest().permitAll()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);
        http.sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
