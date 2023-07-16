package com.tuck.shop.repository;

import com.tuck.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tinashe on 9/7/2023
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
