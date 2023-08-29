package com.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ecommerce.utils.Permission.ADMIN_ACCESS;
import static com.ecommerce.utils.Permission.USER_ACCESS;
import static com.ecommerce.utils.Role.ADMIN;
import static com.ecommerce.utils.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/auth/**")
                                .permitAll()
                                .requestMatchers("/api/user/**").hasRole(ADMIN.name())
                                .requestMatchers("/api/purchase/create").hasAnyRole(USER.name(), ADMIN.name())
                                .requestMatchers("/api/purchase/**").hasAuthority(ADMIN_ACCESS.name())
                                .requestMatchers("/api/order-detail/**").hasAnyRole(ADMIN.name(),USER.name())
                                .requestMatchers("/api/product/**").hasRole(ADMIN.name())
                                .requestMatchers("/api/category/**").hasRole(ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }

}
