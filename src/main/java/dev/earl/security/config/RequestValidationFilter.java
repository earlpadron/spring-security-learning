package dev.earl.security.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * CH 5.1 Implementing a custom filter that checks the existence of "Request-id" inside the header
 *
 * The custom filter we add before authentication checks whether the Request-Id header
 * exists. If the header exists on the request, the application forwards the request to be
 * authenticated. If the header doesn’t exist, the application sets the HTTP status 400 Bad Request
 * and returns to the client.
 */
public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String requestId = httpRequest.getHeader("Request-id");
        if(requestId == null || requestId.isBlank()){
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);//Causes the next filter in the chain to be invoked,
        // or if the calling filter is the last filter in the chain, causes the resource at the end of the chain to be invoked
    }
}
