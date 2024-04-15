package dev.earl.security.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * CH 5.9 Extending OncePerRequestFilter
 *
 * We want to avoid logging the same
 * requests multiple times. Spring Security doesn’t guarantee the filter won’t be
 * called more than once, so we have to take care of this ourselves. The easiest
 * way is to implement the filter using the OncePerRequestFilter class. I
 *
 * "charitalics"It supports only HTTP requests, but that’s actually what we
 * always use. The advantage is that it casts the types, and we directly
 * receive the requests as HttpServletRequest and HttpServletResponse.
 * Remember, with the Filter interface, we had to cast the request and the
 * response.
 * "charitalics"You can implement logic to decide if the filter is applied or
 * not. Even if you added the filter to the chain, you might decide it doesn’t
 * apply for certain requests. You set this by overriding the
 * shouldNotFilter(HttpServletRequest) method. By default, the filter
 * applies to all requests.
 * "charitalics"By default, a OncePerRequestFilter "charitalics"doesn’t
 * apply to asynchronous requests or error dispatch requests. You can
 * change this behavior by overriding the methods
 * shouldNotFilterAsyncDispatch() and
 * shouldNotFilterErrorDispatch()
 *
 */
public class AuthenticationLoggerFilter extends OncePerRequestFilter {

    private final Logger logger = Logger.getLogger(AuthenticationLoggerFilter.class.getName());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestId = ((HttpServletRequest) request).getHeader("Request-id");

        logger.info("Successfully authenticated request with id " + requestId);

        filterChain.doFilter(request, response);
    }
}
