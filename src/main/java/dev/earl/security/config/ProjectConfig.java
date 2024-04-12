package dev.earl.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfig {

//    private final CustomAuthenticationProvider customAuthenticationProvider;
//
//    public ProjectConfig(CustomAuthenticationProvider customAuthenticationProvider) {
//        this.customAuthenticationProvider = customAuthenticationProvider;
//    }

    /**
     *
     * If the default UserDetailsService is overridden with a custom implementation,
     * a PasswordEncoder must also be declared as they come in a pair
     *
     * SecurityFilterChain allows customization of authentication methods and authorization rules for specific endpoints
     */
//    @Bean //instructs Spring to add the instance returned by the method to the Spring Context
//    public UserDetailsService userDetailsService(){
//        var user = User
//                .withUsername("john")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance(); //NoOpPasswordEncoder is insecure and treats passwords as plain text
//        // its only use is for examples
//    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        /**
//         * httpBasic() – which helped you configure the authentication approach.
//         * Calling this method you instructed your app to accept HTTP Basic as an
//         * authentication method.
//         * 2. authorizeHttpRequests() – which helped you configure the
//         * authorization rules at the endpoint level. Calling this method you
//         * instructed the app how it should authorize the requests received on
//         * specific endpoints.
//         *
//         * Customizer is a contract you implement to define the customization for either
//         * Spring Security element you configure: the authentication, the authorization,
//         * or particular protection mechanisms such as CSRF or CORS
//         *  > Customizer allows configurations to be moved to separate classes for better flexibility
//         */
//        http.httpBasic(Customizer.withDefaults());
//        //http.authenticationProvider(customAuthenticationProvider); if a custom Authentication Provider is used, it can replace
//        //the manual creation of a PasswordEncoder and UserDetailsService beans
//        http.authorizeHttpRequests(
//                c -> c.anyRequest().permitAll()//this makes all endpoints accessible without the need for credentials  .authenticated()
//                );
//        return http.build();
//    }

    /**
     * An alternative to creating a bean of UserDetailsService: SecurityFilterChain can be used to declare the UserDetailsService inside one method
     * BOTH are correct, the other allows values to be injected in another class where they can be used
     * NOTICE: password encoder is still separate
     */
//    @Bean
//    public SecurityFilterChain configure2(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//        http.authorizeHttpRequests(
//                c -> c.anyRequest().authenticated()
//        );
//
//        //create user
//        var user = User.withUsername("john")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        //declaring userDetailsService inside the method
//        var userDetailsService =
//                new InMemoryUserDetailsManager(user);
//
//        http.userDetailsService(userDetailsService); //difference, the http is used to register the UserDetailsService
//        return http.build();
//    }

    /**
     * this Bean uses the custom implemented UserDetailsService - InMemoryUserDetailsService
     */
//    @Bean
//    public UserDetailsService userDetailsServiceCustomInMemory(){
//        UserDetails user = User.withUsername("john")
//                .password("12345")
//                .authorities("read")
//                .build();
//        List<UserDetails> users = List.of(user);
//        return new InMemoryUserDetailsService(users);
//    }


    /**
     * CH 3.3 userDetailsManager allows management of users by allowing CRUD operations
     */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        //custom queries
//        String usersByUsernameQuery =
//                "select username, password, enabled from spring_security.users where username = ?";
//        String authsByUserQuery =
//                "select username, authority from spring_security.authorities where username = ?";
//        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
//        return userDetailsManager;
//
//
////        return new JdbcUserDetailsManager(dataSource);
//    }

    /**
     * CH 4.4 Creating an instance of DelegatingPasswordEncoder
     *
     * The DelegatingPasswordEncoder is just a tool that acts as a
     * PasswordEncoder, so you can use it when you have to choose from a
     * collection of implementations
     *
     * DelegatingPasswordEncoder contains references to a
     * NoOpPasswordEncoder, a BCryptPasswordEncoder, and an
     * SCryptPasswordEncoder, and delegates the default to the
     * BCryptPasswordEncoder implementation. Based on the prefix of the hash,
     * the DelegatingPasswordEncoder uses the right PasswordEncoder
     * implementation for matching the password. This prefix has the key that
     * identifies the password encoder to be used from the map of encoders. If there
     * is no prefix, the DelegatingPasswordEncoder uses the default encoder. The
     * default PasswordEncoder is the one given as the first parameter when
     * constructing the DelegatingPasswordEncoder instance. For the code in
     * listing 4.4, the default PasswordEncoder is bcrypt.
     *
     * remember that the curly braces are
     * mandatory in the prefix. ex {bcrypt}$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG
     *
     *
     * For convenience, Spring Security offers a way to create a
     * DelegatingPasswordEncoder that has a map to all the standard provided
     * implementations of PasswordEncoder. The PasswordEncoderFactories class
     * provides a createDelegatingPasswordEncoder() static method that returns
     * an implementation of DelegatingPasswordEncoder with a full set of
     * PasswordEncoder mappings and bcrypt as a default encoder:
     *
     * Also learned about SSCM which has utility methods for cryptography - salt generation, encryption/decryption
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(16384,8,1,32,64));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }





}
