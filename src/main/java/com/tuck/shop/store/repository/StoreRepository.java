package com.tuck.shop.store.repository;

import com.tuck.shop.store.Store;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tinashe on 16/11/2023
 */
public interface StoreRepository extends CrudRepository<Store, Long> {
    Store findStoreByStoreName(String storeName);
}
