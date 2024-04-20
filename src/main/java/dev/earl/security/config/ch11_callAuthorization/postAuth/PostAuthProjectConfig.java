//package dev.earl.security.config.ch11_callAuthorization.postAuth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableMethodSecurity
//public class PostAuthProjectConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//
//        var user1 = User.withUsername("natalie")
//                .password("12345")
//                .authorities("write")
//                .build();
//
//        var user2 = User.withUsername("emma")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//}
