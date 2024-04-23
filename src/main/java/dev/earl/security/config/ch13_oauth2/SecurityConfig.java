package dev.earl.security.config.ch13_oauth2;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;

@Configuration
public class SecurityConfig {

    /**
     * Configuration Filter for Protocol endpoints
     */
    @Bean
    @Order(1)
    public SecurityFilterChain asFilterChain(HttpSecurity http) throws Exception {

        //applyDefaultSecurity() - utility method to define a minimal set of configurations that can be overriden later if needed
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        //oidc() - enables OpenID Connect protocol
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        //specify the authentication page to which the app needs to redirect the user on login since authorization code grant type will be used
        //default path in Spring wep app is "/login"
        http.exceptionHandling(e -> e.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));

        return http.build();
    }

    @Bean
    @Order(2) //this annotation is needed since there are multiple SecurityFilterChain instances
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withUsername("bill")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     *  <dependency>
     *       <groupId>org.springframework.boot</groupId>
     *       <artifactId>spring-boot-starter-oauth2-authorization-server</artifactId>
     *     </dependency>
     * @return
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .clientAuthenticationMethod(
                        ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                )
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://www.mannuing.com/authorized")
                .scope(OidcScopes.OPENID)
                .build();
    }
}
