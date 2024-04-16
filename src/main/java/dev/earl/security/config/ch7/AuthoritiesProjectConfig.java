//package dev.earl.security.config.ch7;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class AuthoritiesProjectConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//        var user1 = User.withUsername("john")
//                .password("12345")
//                .authorities("READ")
//                .build();
//
//        var user2 = User.withUsername("jane")
//                .password("12345")
//                .authorities("WRITE")
//                .build();
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
//    /**
//     * The application needs, first, to authenticate the request and then, based on the user’s
//     * authorities, the app decides whether to allow the call.
//
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());//use http basic forms(default)
//
//        String expression = "hasAuthority('read') and !hasAuthority('delete')"; //SpEL expression to be used with AuthorizationManager
//
//        http.authorizeHttpRequests(
//                c -> c.anyRequest()
//                        //.hasAuthority("WRITE") //restrict access to all methods to users with "WRITE" authority
//                        .hasAnyAuthority("WRITE", "READ") //restrict access to all methods to users only with "WRITE" OR "READ authority
//                        //.access(new WebExpressionAuthorizationManager(expression)) access() is used for more complex authorities that is not typically recommended
//
//        );
//
//        return http.build();
//    }
//
//
//
//
//
//}
