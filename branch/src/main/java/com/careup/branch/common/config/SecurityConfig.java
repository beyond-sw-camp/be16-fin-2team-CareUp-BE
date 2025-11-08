package com.careup.branch.common.config;

import com.careup.branch.common.auth.JwtAccessDeniedHandler;
import com.careup.branch.common.auth.JwtAuthenticationEntryPoint;
import com.careup.branch.common.auth.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtTokenFilter jwtTokenFilter,
                                                   JwtAuthenticationEntryPoint entryPoint,
                                                   JwtAccessDeniedHandler deniedHandler
    ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(
                        "/branch-service/auth/login",
                        "/branch-service/actuator/**",
                        "/branch-service/public/**",
                        "/branch-service/health",
                        "/branch-service/auth/login",
                        "/branch-service/auth/refresh",
                        "/branch-service/auth/logout",
                        "/branch-service/auth/password/forgot",
                        "/branch-service/auth/password/reset",
                        "/branch-service/auth/introspect",
                        "/branch-service/inventory/**",
                        "/branch-service/categories/**",
                        "/branch-service/products/**",
                        "/branch-service/purchase-orders/**",
                        "/branch-service/api/products/public/**",
                        "/branch-service/sales-forecast/**",
                        "/branch-service/sales/**",
                        "/branch-service/chatbot/**",
                        "/branch-service/branch/list-by-ids",   // 지점 목록 조회 공개 (ordering 서비스에서 사용)
                        "/branch-service/employees/internal/**", // 내부 API용 직원 정보 조회 (ordering 서비스에서 사용)
                        "/branch-service/sse/**",
                        "/actuator/**",
                        "/public/**",
                        "/health",
                        "/auth/login",
                        "/auth/refresh",
                        "/auth/logout",
                        "/auth/password/forgot",
                        "/auth/password/reset",
                        "/auth/introspect",
                        "/inventory/**",
                        "/categories/**",
                        "/products/**",
                        "/purchase-orders/**",
                        "/api/products/public/**",
                        "/sales-forecast/**",
                        "/sales/**",
                        "/chatbot/**",
                        "/branch/list-by-ids",
                        "/employees/internal/**",
                        "/sse/**"
                ).permitAll()
                .anyRequest().authenticated()
        );

        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(deniedHandler)
        );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
