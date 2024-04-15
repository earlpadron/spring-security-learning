package dev.earl.security.config.ch6_customAuthHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This interfaces let
 * you implement an object through which you can apply the logic executed for
 * authentication. If you want to customize the logic for successful
 * authentication, you can define an AuthenticationSuccessHandler.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * CH 6.22
     * implementing the
     * onAuthenticationSuccess()method to make different redirects depending
     * on the granted authorities of the logged-in user.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var auth = authorities.stream()
                .filter(a -> a.getAuthority().equals("read"))
                .findFirst();

        if(auth.isPresent()){
            response.sendRedirect("/home"); //successful authentication -> send to home page
        } else {
            response.sendRedirect("/error"); //failed authentication -> send to error page
        }

    }
}
