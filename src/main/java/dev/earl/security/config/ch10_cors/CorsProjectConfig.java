package dev.earl.security.config.ch10_cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /**
         * An alternative to @CrossOrigins directly on the method that exposes the endpoint
         *
         * Use this can be beneficial for maintainability when the application gets larger
         * TYPICALLY, CorsConfigurationSource should exist in a separate method as the codebase becomes larger!!
         */
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedOrigins(List.of("example.com", "example.org"));
                config.setAllowedMethods(List.of("GET", "PUT", "POST", "DELETE"));
                config.setAllowedHeaders(List.of("*")); //* means any
                return config;
            } ;
            c.configurationSource(source);
        });

        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();

    }
}
