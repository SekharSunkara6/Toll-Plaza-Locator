package com.example.tollplaza;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { TollPlazaLocatorApplication.class, TestConfig.class })
public class TollPlazaLocatorApplicationTests {

	@Test
	void contextLoads() {
		// Context loads test
	}
}
