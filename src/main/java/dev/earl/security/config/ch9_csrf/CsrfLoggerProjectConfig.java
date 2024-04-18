//package dev.earl.security.config.ch9_csrf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CsrfFilter;
//
//@Configuration
//public class CsrfLoggerProjectConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
//                .authorizeHttpRequests(
//                        c -> c.anyRequest().permitAll()
//                );
//        return http.build();
//    }
//
//}
