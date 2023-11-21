package com.tuck.shop.add_store;

import com.tuck.shop.store.Store;
import com.tuck.shop.store.dto.StoreDTO;
import com.tuck.shop.store.repository.StoreRepository;
import com.tuck.shop.store.service.AddStoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Tinashe on 20/11/2023
 */

@SpringBootTest
public class AddStoreServiceTest {

    @InjectMocks
    AddStoreService addStoreService;

    @Mock
    StoreRepository storeRepository;

    @Autowired
    ModelMapper mapper;

    @Test
    public void addStoreServiceTest(){
        // given (authenticated user can add a store with the Admin role
        //        store cannot have same the name and address
        StoreDTO storeDetails = StoreDTO.builder().
                    storeName("Test Store").
                    storeAddress("1234 Test lane").
                    storeDescription("This is a test store.").
                build();
        Store createdStore;
        // when
        when(storeRepository.save(any(Store.class))).thenReturn(mapper.map(storeDetails, Store.class));
        when(storeRepository.findStoreByStoreName(any(String.class))).thenReturn(mapper.map(storeDetails, Store.class));
        addStoreService.addStore(storeDetails);
        // then
        createdStore = storeRepository.findStoreByStoreName(storeDetails.getStoreName());
        assertThat(createdStore.getStoreName(), is(equalTo(storeDetails.getStoreName())));
    }
}
