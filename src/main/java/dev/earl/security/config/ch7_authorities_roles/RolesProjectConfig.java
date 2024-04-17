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
//public class RolesProjectConfig {
//
//    /**
//     * The names that you give to roles are like the names for authorities—it’s your
//     * own choice. We could say that roles are coarse grained when compared with
//     * authorities. Behind the scenes, anyway, roles are represented using the same
//     * contract in Spring Security, GrantedAuthority. When defining a role, its
//     * name should start with the ROLE_ prefix. At the implementation level, this
//     * prefix specifies the difference between a role and an authority.
//     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//
//        /**
//         * when using the authorities() method, include the ROLE_ prefix. When using the roles()
//         * method, do not include the ROLE_ prefix
//         */
//        var user1 = User.withUsername("john")
//                .password("12345")
//                .authorities("ROLE_ADMIN")
//                //.roles("ADMIN") //alternative to authorities(), NOTICE: DO NOT ADD ROLE_<role> prefix with roles()
//                .build();
//        var user2 = User.withUsername("jane")
//                .password("12345")
//                .authorities("ROLE_MANAGER")
//                //.roles("MANAGER")
//                .build();
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//
//        /**
//         * A critical thing to observe is that we use the ROLE_<prefix> only to declare the
//         * role. But when we use the role, we do it only by its name
//         */
//        http.authorizeHttpRequests(
//                c -> c.anyRequest()
//                        //.denyALl() - denies all requests from all endpoints, opposite of permitAll()
//                        .hasRole("ADMIN")
//        );
//        return http.build();
//    }
//
//
//}
