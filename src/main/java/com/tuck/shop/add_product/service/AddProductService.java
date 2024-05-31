package com.tuck.shop.add_product.service;

import com.tuck.shop.add_product.entity.Product;
import com.tuck.shop.add_product.entity.ProductInfoDTO;
import com.tuck.shop.add_product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Tinashe on 29/11/2023
 */

@Service
public class AddProductService {

    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(ProductInfoDTO productInfoDTO){
        // check mandatory fields are in place
        if (productInfoDTO.getProductCode() == null || productInfoDTO.getProductCode().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product code cannot be null or empty");
        }

        Product addProduct = modelMapper.map(productInfoDTO, Product.class);
        return productRepository.save(addProduct);
    }

    public Product getProduct(String productCode) {
        return productRepository.findByProductCode(productCode);
    }
}
