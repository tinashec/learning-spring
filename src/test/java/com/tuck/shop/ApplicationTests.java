package com.tuck.shop;

import com.tuck.shop.utils.RsaKeyProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@EnableConfigurationProperties({RsaKeyProperties.class})
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
