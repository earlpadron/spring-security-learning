//package dev.earl.security.config;
//
//import dev.earl.security.config.ch6_customAuthHandlers.CustomAuthenticationFailureHandler;
//import dev.earl.security.config.ch6_customAuthHandlers.CustomAuthenticationSuccessHandler;
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
//public class ReqMatchersProjectConfig {
//    public ReqMatchersProjectConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomAuthenticationFailureHandler authenticationFailureHandler) {
////        this.authenticationSuccessHandler = authenticationSuccessHandler;
////        this.authenticationFailureHandler = authenticationFailureHandler;
//    }
//
//    //CH 6.5 - Registering AuthenticationProvider
//    //notice an interface is injected, not the CustomAuthenticationProvider
//    //Spring will know it needs to find an instance within its context and will find CustomAuthenticationProvider
////    private  final AuthenticationProvider authenticationProvider;
////
////    public ReqMatchersProjectConfig(AuthenticationProvider authenticationProvider) {
////        this.authenticationProvider = authenticationProvider;
////    }
//
//    //CH 5.8
////    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;
////
////    public ReqMatchersProjectConfig(StaticKeyAuthenticationFilter staticKeyAuthenticationFilter) {
////        this.staticKeyAuthenticationFilter = staticKeyAuthenticationFilter;
////    }
//
////    private final CustomAuthenticationProvider customAuthenticationProvider;
////
////    public ReqMatchersProjectConfig(CustomAuthenticationProvider customAuthenticationProvider) {
////        this.customAuthenticationProvider = customAuthenticationProvider;
////    }
//
//    /**
//     *
//     * If the default UserDetailsService is overridden with a custom implementation,
//     * a PasswordEncoder must also be declared as they come in a pair
//     *
//     * SecurityFilterChain allows customization of authentication methods and authorization rules for specific endpoints
//     */
////    @Bean //instructs Spring to add the instance returned by the method to the Spring Context
////    public UserDetailsService userDetailsService(){
////        var user = User
////                .withUsername("john")
////                .password("12345")
////                .authorities("read")
////                .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
////
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance(); //NoOpPasswordEncoder is insecure and treats passwords as plain text
//        // its only use is for examples
//    }
//
////    @Bean
////    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
////        /**
////         * httpBasic() – which helped you configure the authentication approach.
////         * Calling this method you instructed your app to accept HTTP Basic as an
////         * authentication method.
////         * 2. authorizeHttpRequests() – which helped you configure the
////         * authorization rules at the endpoint level. Calling this method you
////         * instructed the app how it should authorize the requests received on
////         * specific endpoints.
////         *
////         * Customizer is a contract you implement to define the customization for either
////         * Spring Security element you configure: the authentication, the authorization,
////         * or particular protection mechanisms such as CSRF or CORS
////         *  > Customizer allows configurations to be moved to separate classes for better flexibility
////         */
////        http.httpBasic(Customizer.withDefaults());
////        //http.authenticationProvider(customAuthenticationProvider); if a custom Authentication Provider is used, it can replace
////        //the manual creation of a PasswordEncoder and UserDetailsService beans
////        http.authorizeHttpRequests(
////                c -> c.anyRequest().permitAll()//this makes all endpoints accessible without the need for credentials  .authenticated()
////                );
////        return http.build();
////    }
//
//    /**
//     * An alternative to creating a bean of UserDetailsService: SecurityFilterChain can be used to declare the UserDetailsService inside one method
//     * BOTH are correct, the other allows values to be injected in another class where they can be used
//     * NOTICE: password encoder is still separate
//     */
////    @Bean
////    public SecurityFilterChain configure2(HttpSecurity http) throws Exception {
////        http.httpBasic(Customizer.withDefaults());
////        http.authorizeHttpRequests(
////                c -> c.anyRequest().authenticated()
////        );
////
////        //create user
////        var user = User.withUsername("john")
////                .password("12345")
////                .authorities("read")
////                .build();
////
////        //declaring userDetailsService inside the method
////        var userDetailsService =
////                new InMemoryUserDetailsManager(user);
////
////        http.userDetailsService(userDetailsService); //difference, the http is used to register the UserDetailsService
////        return http.build();
////    }
//
//    /**
//     * this Bean uses the custom implemented UserDetailsService - InMemoryUserDetailsService
//     */
////    @Bean
////    public UserDetailsService userDetailsServiceCustomInMemory(){
////        UserDetails user = User.withUsername("john")
////                .password("12345")
////                .authorities("read")
////                .build();
////        List<UserDetails> users = List.of(user);
////        return new InMemoryUserDetailsService(users);
////    }
//
//
//
////    @Bean
////    public UserDetailsService userDetailsService(DataSource dataSource){
////        //custom queries
////        String usersByUsernameQuery =
////                "select username, password, enabled from spring_security.users where username = ?";
////        String authsByUserQuery =
////                "select username, authority from spring_security.authorities where username = ?";
////        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
////        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
////        return userDetailsManager;
////
////
//////        return new JdbcUserDetailsManager(dataSource);
////    }
//
//    /**
//     * CH 5.1
//     * applying the custom filter(RequestValidationFilter) within the configuration class, we
//     * use the addFilterBefore() method of the HttpSecurity object because we
//     * want the application to execute this custom filter before authentication
//     *
//     * CH 5.8 addFilterAt()
//
//     */
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////            http
////                .addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
////                .addFilterBefore(new RequestValidationFilter(),
////                        BasicAuthenticationFilter.class)
////                .addFilterAfter(new AuthenticationLoggerFilter(),
////                            BasicAuthenticationFilter.class)
////                .authorizeHttpRequests(c -> c.anyRequest().permitAll());
////
////        return http.build();
////    }
//
//    /**
//     * Ch 6.5 - AuthenticationProvider
//     */
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .httpBasic(Customizer.withDefaults())
////                .authenticationProvider(authenticationProvider)
////                .authorizeHttpRequests(c -> c.anyRequest().authenticated());
////        return http.build();
////    }
//
//    /**
//     * 6.19 - Form-based Login with an html page to redirect successful authentication
//     */
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.formLogin(c -> c.defaultSuccessUrl("/home", true));
////        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
////        return http.build();
////    }
//
//    /**
//     * CH 6.24 - Register Handler objects in the configuration class
//     */
////    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
////    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
////    // Omitted constructor
////    @Bean
////    public UserDetailsService uds() {
////        var uds = new InMemoryUserDetailsManager();
////        uds.createUser(
////                User.withDefaultPasswordEncoder()
////                        .username("john")
////                        .password("12345")
////                        .authorities("read")
////                        .build()
////        );
////        uds.createUser(
////                User.withDefaultPasswordEncoder()
////                        .username("bill")
////                        .password("12345")
////                        .authorities("write")
////                        .build()
////        );
////        return uds;
////    }
////    @Bean
////    public SecurityFilterChain configure(HttpSecurity http)
////            throws Exception {
////        http.formLogin(c ->
////                c.successHandler(authenticationSuccessHandler)
////                        .failureHandler(authenticationFailureHandler)
////        );
////        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
////        return http.build();
////    }
//}
//
//
