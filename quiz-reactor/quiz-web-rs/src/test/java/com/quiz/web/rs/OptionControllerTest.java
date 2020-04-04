package com.quiz.web.rs;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import com.quiz.coreservice.manager.OptionManagerImpl;
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

@TestPropertySource(locations = "/application.properties")
public class OptionControllerTest {

	private static final String VALID_ID = "1";
	private static final String VALID_VALUE = "valid";
	private static final String UPDATE_VALUE = "updated";
	@Mock
	private OptionManagerImpl manager;
	@InjectMocks
	private OptionController controller;
	private OptionWebRequest opWebReq = OptionWebRequest.builder().withValue(VALID_VALUE).build();
	private OptionWebResponse opWebResp = OptionWebResponse.builder().withValue(opWebReq.getValue()).build();
	
	
	
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
	
	@Test
	public void givenExistentDocumentShouldFindByIdOk() {
		Mockito.doReturn(opWebResp).when(this.manager).findById(ArgumentMatchers.eq(VALID_ID));
		OptionWebResponse actual = this.controller.findById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(opWebResp));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Mockito.doReturn(Boolean.TRUE).when(this.manager).deleteById(ArgumentMatchers.eq(VALID_ID));
		Boolean actual = this.controller.deleteById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(opWebResp).when(this.manager).create(ArgumentMatchers.eq(opWebReq));
		OptionWebResponse actual = this.controller.create(opWebReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(opWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		OptionWebRequest updatedReq = opWebReq.cloneBuilder().withValue(UPDATE_VALUE).build();
		OptionWebResponse updatedResp = opWebResp.cloneBuilder().withValue(updatedReq.getValue()).build();
		Mockito.doReturn(updatedResp).when(this.manager).create(ArgumentMatchers.eq(updatedReq));
		OptionWebResponse actual = this.controller.create(updatedReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(updatedResp));
	}
}
