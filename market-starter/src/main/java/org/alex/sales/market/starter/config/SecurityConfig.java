package org.alex.sales.market.starter.config;

import lombok.RequiredArgsConstructor;
import org.alex.sales.market.security.impl.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String[] REQUEST_MATCHERS = {"/api/auth/login", "/api/auth/token", "/api/auth/repair"};

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .exceptionHandling()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(REQUEST_MATCHERS)
                .permitAll()
                .anyRequest()
                .authenticated();
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private AntPathRequestMatcher[] getMatchers() {
        List<AntPathRequestMatcher> matchers = new ArrayList<>();
        Arrays.stream(REQUEST_MATCHERS).forEach(path -> matchers.add(new AntPathRequestMatcher(path)));
        return matchers.toArray(AntPathRequestMatcher[]::new);
    }

}
