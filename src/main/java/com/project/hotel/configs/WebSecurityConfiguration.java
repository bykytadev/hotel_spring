package com.project.hotel.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.hotel.entites.enums.UserRole;
import com.project.hotel.services.jwt.UserService;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    public static final String[] SWAGGER_WHITELIST = {
        "/api-docs",
        "/api-docs/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/webjars/swagger-ui/**",
    };

    public static final String[] ACTUATOR_WHITELIST = {
        "/api/actuator/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/auth/**")
                .permitAll()
                .requestMatchers("/api/admin/**")
                .hasAnyAuthority(UserRole.ADMIN.name())
                .requestMatchers("/api/customer/**")
                .hasAnyAuthority(UserRole.CUSTOMER.name())
                .requestMatchers(SWAGGER_WHITELIST)
                .permitAll()
                .requestMatchers(ACTUATOR_WHITELIST)
                .permitAll()
                .anyRequest()
                .authenticated())
            .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
