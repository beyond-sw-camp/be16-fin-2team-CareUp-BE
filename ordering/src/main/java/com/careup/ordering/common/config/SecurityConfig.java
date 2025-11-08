package com.careup.ordering.common.config;

import com.careup.ordering.common.auth.JwtAccessDeniedHandler;
import com.careup.ordering.common.auth.JwtAuthenticationEntryPoint;
import com.careup.ordering.common.auth.JwtTokenFilter;
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
                                                   JwtAccessDeniedHandler deniedHandler) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**", "/public/**", "/health").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(
                        "/ordering-service/auth/customers/signup",
                        "/ordering-service/auth/customers/login",
                        "/ordering-service/auth/customers/refresh",
                        "/ordering-service/auth/customers/logout",
                        "/ordering-service/auth/customers/password/forgot",
                        "/ordering-service/auth/customers/password/reset",
                        "/ordering-service/auth/customers/oauth/state",
                        "/ordering-service/auth/customers/oauth/google",
                        "/ordering-service/auth/customers/oauth/kakao",
                        "/ordering-service/auth/customers/oauth/update",
                        "/auth/customers/signup",
                        "/auth/customers/login",
                        "/auth/customers/refresh",
                        "/auth/customers/logout",
                        "/auth/customers/password/forgot",
                        "/auth/customers/password/reset",
                        "/auth/customers/oauth/state",
                        "/auth/customers/oauth/google",
                        "/auth/customers/oauth/kakao",
                        "/auth/customers/oauth/update"
                ).permitAll()

                .requestMatchers(HttpMethod.GET,
                        "/products/**",
                        "/api/products/**",
                        "/api/rank/**",
                        "/ordering-service/products/**",
                        "/ordering-service/api/products/**",
                        "/ordering-service/api/rank/**"
                ).permitAll()

                .requestMatchers(HttpMethod.POST, "/products/**", "/api/products/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.PUT, "/products/**", "/api/products/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.DELETE, "/products/**", "/api/products/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")

                .requestMatchers(HttpMethod.GET,
                        "/api/public/products",
                        "/api/public/products/with-branches",
                        "/api/public/products/search",
                        "/ordering-service/api/public/products",
                        "/ordering-service/api/public/products/with-branches",
                        "/ordering-service/api/public/products/search"
                ).permitAll()

                .requestMatchers(HttpMethod.GET, "/api/categories/**", "/ordering-service/api/categories/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")

                .requestMatchers(HttpMethod.GET, "/api/promotions/**", "/ordering-service/api/promotions/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/promotions/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.PUT, "/api/promotions/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.DELETE, "/api/promotions/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")

                .requestMatchers(HttpMethod.GET, "/api/product-inquiries/**", "/ordering-service/api/product-inquiries/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/product-inquiries/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/product-inquiries/**").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/product-inquiries/**").hasRole("CUSTOMER")

                .requestMatchers(HttpMethod.GET,
                        "/inventory/branch-products/**",
                        "/ordering-service/inventory/branch-products/**"
                ).permitAll()

                .requestMatchers(
                        "/inventory/flow",
                        "/inventory/adjustment-history",
                        "/inventory/branch/**",
                        "/inventory/safety-stock",
                        "/inventory/adjust",
                        "/inventory/**"
                ).hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER","STAFF")

                .requestMatchers(HttpMethod.GET, "/coupons/**", "/ordering-service/coupons/**", "/api/coupons/**", "/ordering-service/api/coupons/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/coupons/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.PUT, "/api/coupons/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")
                .requestMatchers(HttpMethod.DELETE, "/api/coupons/**").hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")

                .requestMatchers(HttpMethod.GET,
                        "/api/orders/**",
                        "/ordering-service/api/orders/**"
                ).hasAnyRole("CUSTOMER", "HQ_ADMIN", "BRANCH_ADMIN", "FRANCHISE_OWNER", "STAFF")

                .requestMatchers(HttpMethod.PUT,
                        "/api/orders/*/approve",
                        "/api/orders/*/reject",
                        "/ordering-service/api/orders/*/approve",
                        "/ordering-service/api/orders/*/reject"
                ).hasAnyRole("HQ_ADMIN", "BRANCH_ADMIN", "FRANCHISE_OWNER", "STAFF")

                .requestMatchers(HttpMethod.POST,
                        "/api/orders",
                        "/ordering-service/api/orders"
                ).hasRole("CUSTOMER")

                .requestMatchers(HttpMethod.DELETE,
                        "/api/orders/**",
                        "/ordering-service/api/orders/**"
                ).hasRole("CUSTOMER")

                .requestMatchers(
                        "/api/cart/**",
                        "/api/payments/**",
                        "/ordering-service/api/cart/**",
                        "/ordering-service/api/payments/**"
                ).hasRole("CUSTOMER")

                .requestMatchers(
                        "/admin/**",
                        "/management/**",
                        "/product-admin/**",
                        "/api/loyal-customers/**"
                ).hasAnyRole("HQ_ADMIN","BRANCH_ADMIN","FRANCHISE_OWNER")

                .requestMatchers("/sales/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/rec/**", "/ordering-service/rec/**").permitAll()

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
