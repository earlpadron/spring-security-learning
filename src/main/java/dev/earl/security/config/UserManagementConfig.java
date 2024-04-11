//package dev.earl.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///**
// * This class is created to promote separation of concerns instead of combining the User Manage with Web Authorization configurations in one class
// *
// * paired with WebAuthorizationConfig classs
// */
//@Configuration
//public class UserManagementConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("john")
//                .password("12345")
//                .authorities("read")
//                .build();
//        userDetailsService.createUser(user);
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
