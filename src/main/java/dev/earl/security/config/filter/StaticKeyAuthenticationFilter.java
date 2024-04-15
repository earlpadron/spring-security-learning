package dev.earl.security.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Ch 5.7
 * This class reads the value of the static
 * key from the properties file and verifies if the value of the Authorization
 * header is equal to it. If the values are the same, the filter forwards the request
 * to the next component in the filter chain. If not, the filter sets the value 401
 * Unauthorized to the HTTP status of the response without forwarding the
 * request in the filter chain
 *
 * It takes the place of BasicAuthenticationFilter, BUT it does not replace it
 */
@Component
public class StaticKeyAuthenticationFilter implements Filter {
    @Value("${authorization.key}")
    private String authKey;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String authentication = httpRequest.getHeader("Authorization");
        if(authKey.equals(authentication)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
