package com.quiz.web.rs;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import com.quiz.coreservice.manager.OptionManagerImpl;
import com.quiz.response.OptionWebResponse;

@TestPropertySource(locations = "/application.properties")
public class OptionControllerTest {

	@Mock
	private OptionManagerImpl manager;
	@InjectMocks
	private OptionController controller;
	private OptionWebResponse opWebResp = OptionWebResponse.builder().withValue("asd").build();
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(opWebResp)).when(this.manager).findAll();
		List<OptionWebResponse> actual = this.controller.findAll().getBody();
		MatcherAssert.assertThat(actual, Matchers.hasItems(Matchers.is(opWebResp)));
	}
}
