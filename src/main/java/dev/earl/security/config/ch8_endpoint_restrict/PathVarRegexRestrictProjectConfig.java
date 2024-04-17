//package dev.earl.security.config.ch8_endpoint_restrict;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class PathVarRegexRestrictProjectConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//
//        http.authorizeHttpRequests(            //using regex to restrict values in the path variable
//                c -> c.requestMatchers("/product/{code:^[0-9]*$}").permitAll() //only allows digits in the path variable
//                        .anyRequest().denyAll()
//        );
//
//
//        return http.build();
//    }
//}
