package com.tuck.shop.store.service;

import com.tuck.shop.store.Store;
import com.tuck.shop.store.dto.StoreDTO;
import com.tuck.shop.store.repository.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tinashe on 16/11/2023
 */
@Service
public class AddStoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ModelMapper mapper = new ModelMapper();

    public void addStore(StoreDTO storeDetails) {
        // what's required to create a store? a valid user, that is also an admin
        // can you have multiple stores with the same name - yes, but not same address?
        storeRepository.save(mapper.map(storeDetails, Store.class));
    }
}
