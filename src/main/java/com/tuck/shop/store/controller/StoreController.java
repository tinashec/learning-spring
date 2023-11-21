package com.tuck.shop.store.controller;

import com.tuck.shop.store.dto.StoreDTO;
import com.tuck.shop.store.service.AddStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tinashe on 16/11/2023
 */
@RestController
@RequestMapping (path = "/store")
public class StoreController {

    @Autowired
    AddStoreService storeService;

    @PostMapping (path = "/add")
    @ResponseStatus (HttpStatus.CREATED)
    public void addStore(@RequestBody StoreDTO storeDetails){
        storeService.addStore(storeDetails);
    }
}
