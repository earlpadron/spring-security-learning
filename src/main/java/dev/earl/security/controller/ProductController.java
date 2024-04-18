package dev.earl.security.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Configuration
public class ProductController {
    private Logger logger =
            Logger.getLogger(ProductController.class.getName());

    @GetMapping("/product/{code}")
    public String productCode(@PathVariable String code){
        return code;
    }

    @PostMapping("/product/add")
    public String add(@RequestParam String name){
        logger.info("Adding product " + name);
        return "main.html";
    }



}
