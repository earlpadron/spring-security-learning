//package dev.earl.security.config.ch9_csrf;
//
//import jakarta.servlet.*;
//import org.springframework.security.web.csrf.CsrfToken;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//public class CsrfTokenLogger implements Filter {
//
//    private final Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        var csrfToken = (CsrfToken) request.getAttribute("_csrf");
//        logger.info("CSRF token " + csrfToken.getToken());
//
//        chain.doFilter(request, response);
//
//    }
//}
