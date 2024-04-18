//package dev.earl.security.config.ch9_csrf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class CsrfLoginProjectConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//
//        var u1 = User.withUsername("mary")
//                .password("1234")
//                .authorities("READ")
//                .build();
//
//        manager.createUser(u1);
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.formLogin(
//                c -> c.defaultSuccessUrl("/main", true)
//        );
//
//        http.authorizeHttpRequests(
//                c -> c.anyRequest().authenticated()
//        );
//
//        return http.build();
//    }
//}
