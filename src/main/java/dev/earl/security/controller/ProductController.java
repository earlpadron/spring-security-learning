package dev.earl.security.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
public class ProductController {

    @GetMapping("/product/{code}")
    public String productCode(@PathVariable String code){
        return code;
    }


}
