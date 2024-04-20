//package dev.earl.security.config.ch12_filter.prefilter;
//
//import org.springframework.cglib.proxy.NoOp;
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
//public class ProjectConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//
//        var u1 = User.withUsername("nikolai")
//                .password("1234")
//                .authorities("read")
//                .build();
//
//        var u2 = User.withUsername("julient")
//                .password("1234")
//                .authorities("write")
//                .build();
//
//        manager.createUser(u1);
//        manager.createUser(u2);
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
