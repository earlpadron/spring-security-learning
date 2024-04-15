package dev.earl.security.config.ch6_customAuthHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * CH 6.23
 *
 * There are situations in practical scenarios when a client expects a certain
 * format of the response in case of failed authentication. They may expect a
 * different HTTP status code than 401 Unauthorized or additional information
 * in the body of the response. The most typical case I have found in
 * applications is to send a "charitalics"request identifier. This request identifier
 * has a unique value used to trace back the request among multiple systems,
 * and the application can send it in the body of the response in case of failed
 * authentication. Another situation is when you want to sanitize the response to
 * make sure that the application doesn’t expose sensitive data outside of the
 * system. You might want to define custom logic for failed authentication
 * simply by logging the event for further investigation
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        try{
            response.setHeader("failed", LocalDateTime.now().toString());
            response.sendRedirect("/error");
        } catch(IOException ex){
            throw new RuntimeException(ex);
        }

    }
}
