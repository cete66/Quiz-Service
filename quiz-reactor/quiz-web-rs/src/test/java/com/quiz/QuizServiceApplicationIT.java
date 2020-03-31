package com.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class QuizServiceApplicationIT {

	@Test
	public void contextLoads() {
		
	}
}
