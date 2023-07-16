package com.tuck.shop.controller;

import com.tuck.shop.entity.Product;
import com.tuck.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Tinashe on 21/6/2023
 */
@RestController
@RequestMapping (path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping (path = "/sample")
    public Product product (@RequestParam(value = "name", defaultValue = "Stock") String name){
        return new Product(name, BigDecimal.valueOf(155));
    }

    @PostMapping (path = "/add")
    @ResponseStatus (HttpStatus.CREATED)
    public @ResponseBody Product addProduct(@RequestParam String name,
                                          @RequestParam String price){
        Product product = new Product(name, new BigDecimal(price));
        return productRepository.save(product);
    }

    @GetMapping (path = "/all")
    public @ResponseBody Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping (path = "/{id}")
    public @ResponseBody Optional<Product> getProductBy(@PathVariable Integer id){
        return productRepository.findById(id);
    }
}
