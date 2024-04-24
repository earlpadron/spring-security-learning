package dev.earl.security.config.ch13_oauth2;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
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
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
public class SecurityConfig {

    /**
     * Configuration Filter for Protocol endpoints
     *
     * http://localhost:8080/.well-known/openid-configuration list of endpoint paths the authorization server exposes (GET METHOD, no authentication needed)
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
     The details we specified when creating the RegisteredClient instance are
     the following:

     > A unique internal ID – value that uniquily identifies the client and has a
         purpose only in the internal app processes.
     > A client ID - external client identifier similar to what a username is for
         the user.
     > A client secret – similar to what a password is for a user.
         The client authentication method – tells how the authorization server
         expects the client to authenticate when sending requests for access
         tokens.
     > Authorization grant type – A grant type allowed by the authorization
        server for this client. A client might use multiple grant types.
     > Redirect URI – One of the URI addresses the authorization server allows
         the client to request a redirect for providing the authorization code in
         case of the authorization code grant type.
     > A scope – Defines a purpose for the request of an access token. The
        scope can be used later in authorization rules

     In a real-world app, the app would keep all these details in a database from
     where your RegisteredClientRepository custom implementation would
     retrieve them.
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
                /**
                 * it's possible to add multiple grant types :client credentials, refresh token, and authorization code
                 */
//                .authorizationGrantType(
//                        AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .authorizationGrantType(
//                        AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://www.manning.com/authorized") //can also specify multiple redirect uris
                .scope(OidcScopes.OPENID)// can also have multiple scopes
                .build();

        //this registered client shows a client that is able to use client credentials grant type
//        RegisteredClient registeredClientClientCredentialsGrant = RegisteredClient
//                .withId(UUID.randomUUID().toString())
//                .clientId("clientGrant")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                  //notice redirectUri() is not present with client credentials grant type as it directly calls the auth server
//                .scope("CUSTOM")
//                .build();

        /**
         * This shows how to configure a registered client to use opaque tokens.
         * Remember that opaque tokes can be used with any grant type. In this section,
         * I'll use the client credentials grant type to keep things simple and allow you to
         * focus on the discussed subject. You can as well generate opaque tokens with
         * the authorization code grant type.
         */
//        RegisteredClient registeredClient1OpaqueToken = RegisteredClient
//                .withId(UUID.randomUUID().toString())
//                .clientId("opaqueClient")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                //notice: tokenSettings was added to change the access token format
//                .tokenSettings(TokenSettings.builder()
//                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE).
//                          .accessTokenTimeToLive(Duration.ofHours(12))  // to change the lifetime of token, typically it is 10-30 minutes. default is 5 min
//                          .build();
//                .scope("CUSTOM")
//                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    /**
     * Key Pair management must be configured if authorization server uses **non-opaque** tokens
     *
     * For non-opaque tokens, the authorization server uses private
     * keys to sign the tokens and provides the clients with public keys they can use
     * to validate the tokens’ authenticity.
     *
     * For this example, I create a key pair
     * programmatically and add it to the set of keys the authorization server can
     * use. !!! In a real-world app, the app would read the keys from a location where
     * they’re safely stored (such as a vault configured in the environment)!!!
     *
     * However, remember that in a real app, it doesn’t make sense
     * to generate new keys every time the app restarts (like it happens in our case).
     * If that happens for a real app, every time a new deployment occurs, the
     * tokens that were already issued would not work anymore (since they can’t be
     * validated anymore with the existing keys).
     */

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet= new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Finally, the last component we need to add to our minimal configuration is an
     * AuthorizationServerSettings object (listing 14.6). This object allows you
     * to customize all the endpoints paths that the authorization server exposes. If
     * you simply create the object as shown in listing 14.6, the endpoints paths will
     * get some defaults that we’ll analyze later in this section.
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().build();
    }

}
