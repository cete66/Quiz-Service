package com.quiz.web.rs;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import com.quiz.coreservice.manager.OptionManagerImpl;

@TestPropertySource(locations = "/application.properties")
public class OptionControllerTest {

	@Mock
	private OptionManagerImpl manager;
	@InjectMocks
	private OptionController controller;
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
}
