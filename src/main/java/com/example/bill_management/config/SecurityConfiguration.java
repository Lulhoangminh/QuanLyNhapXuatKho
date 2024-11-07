package com.example.bill_management.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
//    private final AuthenticationProvider authenticationProvider;
    private final MyAccessDeniedHandler myAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                // Turn off "csrf" if using api stateless and jwt
                .csrf(AbstractHttpConfigurer::disable)

                // cross-origin resource sharing: permit different domain can be accessed.
                // Customized cors is defined in "corsConfigurationSource" method.
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry

                                // Endpoints allow all users accessing.
                                .requestMatchers("/api/**").permitAll()

                                // Endpoints allow users with role

                                // Remaining endpoints
                                .anyRequest()
                                .authenticated() //
                        )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.accessDeniedHandler(myAccessDeniedHandler))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore()
        ;

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        // FE or others url domains will be injected
        config.setAllowedOrigins(List.of("*"));

        // HTTP methods
        config.setAllowedMethods(List.of("POST", "GET", "DELETE", "PUT"));

        // Headers
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Cookies, Session
        config.setAllowCredentials(true);

        // Config "cors" for endpoints "/api/ ... "
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return source;
    }
}
