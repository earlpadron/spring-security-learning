//package dev.earl.security.config;
//
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//import java.util.Arrays;
//
//public class CustomAuthenticationProvider implements AuthenticationProvider {
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
//}
