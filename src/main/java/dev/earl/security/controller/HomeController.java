package dev.earl.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //notice this is a controller, not a rest controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        /**
         * Spring does not send the value returned by the method in the HTTP
         * response. Instead, it finds and renders the view with the name home.html.
         */
        return "home.html";
    }
}
