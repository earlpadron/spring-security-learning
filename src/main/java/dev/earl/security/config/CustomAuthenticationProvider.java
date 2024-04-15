package dev.earl.security.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * CH 6 - AuthenticationProvider custom implementation
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * make use of the UserDetailsService implementation to get the
     * UserDetails. If the user doesn’t exist, the loadUserByUsername() method
     * should throw an AuthenticationException. In this case, the authentication
     * process stops, and the HTTP filter sets the response status to HTTP 401
     * Unauthorized. If the username exists, we can check further the user’s
     * password with the matches() method of the PasswordEncoder from the
     * context. If the password does not match, then again, an
     * AuthenticationException should be thrown. If the password is correct, the
     * AuthenticationProvider returns an instance of Authentication marked as
     * “authenticated,” which contains the details about the request.
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if(passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    user.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Something went wrong!");
        }
    }

    /**
     * UsernamePasswordAuthenticationToken class is an implementation of the Authentication interface and
     * represents a standard authentication request with username and password.
     */
    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }


//    /**
//     * As you can see, here the condition of the if-else clause is replacing the
//     * responsibilities of UserDetailsService and PasswordEncoder. Your are not
//     * required to use the two beans, but if you work with users and passwords for
//     * authentication, I strongly suggest you separate the logic of their management.
//     * @param authentication the authentication request object.
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = String.valueOf(authentication.getCredentials());
//
//        if("john".equals(username) && "12345".equals(password)){
//            return new UsernamePasswordAuthenticationToken(
//                    username, password, Arrays.asList()
//            );
//        } else {
//            throw new AuthenticationCredentialsNotFoundException("Error!");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
}
