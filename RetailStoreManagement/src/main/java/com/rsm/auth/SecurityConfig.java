package com.rsm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())
          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(auth -> auth
        		    // public
        		    .requestMatchers("/auth/login").permitAll()
        		    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        		    // READ-ONLY for all authenticated users (normal user can see lists/detail)
        		    .requestMatchers(HttpMethod.GET,
        		        "/api/inventory/**",
        		        "/api/parties/**",
        		        "/auth/user/**"
        		    ).authenticated()

        		    // WRITE operations (POST/PUT/DELETE) reserved for ADMIN/MANAGER
        		    .requestMatchers(HttpMethod.POST,
        		        "/api/inventory/**",
        		        "/api/parties/**",
        		        "/auth/user/**",
        		        "/api/purchase/**"
        		    ).hasAnyRole("ADMIN", "MANAGER")

        		    .requestMatchers(HttpMethod.PUT,
        		        "/api/inventory/**",
        		        "/api/parties/**",
        		        "/auth/user/**"
        		    ).hasAnyRole("ADMIN", "MANAGER")

        		    .requestMatchers(HttpMethod.DELETE,
        		        "/api/inventory/**",
        		        "/api/parties/**",
        		        "/auth/user/**"
        		    ).hasAnyRole("ADMIN", "MANAGER")

        		    // other APIs (products, sales, purchase) – any logged-in user
        		    .requestMatchers("/api/products/**", "/api/sales/**")
        		        .authenticated()

        		    .anyRequest().permitAll()
          )
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
