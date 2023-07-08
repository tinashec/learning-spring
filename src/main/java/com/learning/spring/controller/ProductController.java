package com.learning.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tinashe on 21/6/2023
 */
@RestController
public class ProductController {

    @GetMapping("/product")
    public Product product (@RequestParam(value = "name", defaultValue = "Stock") String name){
        return new Product(12, name);
    }
}
