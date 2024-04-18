//package dev.earl.security.config.ch9_csrf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.security.web.util.matcher.RegexRequestMatcher;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//@Configuration
//public class ExcludingCsrfProjectConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//        http.csrf(c -> {
//                  c.ignoringRequestMatchers("/ciao");
//
//            //matching path with MvcRequestMatcher
////                HandlerMappingIntrospector introspector = new HandlerMappingIntrospector();
////                MvcRequestMatcher r = new MvcRequestMatcher(introspector, "/ciao");
////                c.ignoringRequestMatchers(r);
//
//            //matching path with RegexRequestMatcher
////                 String httpMethod = HttpMethod.POST.name();
////                 String pattern = ".*[0-9].*";
////                 RegexRequestMatcher r = new RegexRequestMatcher(pattern , httpMethod);
////                 c.ignoringRequestMatchers(r);
//
//        });//ignore csrf on endpoint "/ciao"
//
//        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
//
//        return http.build();
//    }
//}
