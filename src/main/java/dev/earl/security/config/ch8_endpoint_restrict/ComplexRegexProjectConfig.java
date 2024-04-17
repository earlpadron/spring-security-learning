package dev.earl.security.config.ch8_endpoint_restrict;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ComplexRegexProjectConfig {
    
    @Bean
    public UserDetailsService userDetailsService(){
        var manager = new InMemoryUserDetailsManager();
        
        UserDetails user1 = User.withUsername("john")
                .password("1234")
                .authorities("read")
                .build();
        
        UserDetails user2 = User.withUsername("jane")
                .password("1234")
                .authorities("read","premium")
                .build();
        
        manager.createUser(user1);
        manager.createUser(user2);
        
        return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        
        http.authorizeHttpRequests(
                c -> c.requestMatchers(".*/(us|uk|ca)+/(en|fr).*")
                        .authenticated()
                        .anyRequest()
                        .hasAuthority("premium")
        );

        return http.build();
    }
}
