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

import com.quiz.coreservice.manager.QuestionManagerImpl;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;

@TestPropertySource(locations = "/application.properties")
public class QuestionControllerTest {

	private static final String VALID_ID = "1";
	private static final String VALID_VALUE = "valid";
	private static final String Q1 = "q1";
	private static final String UPD_Q1 = "updQ1";
	@Mock
	private QuestionManagerImpl manager;
	@InjectMocks
	private QuestionController controller;
	private final OptionWebRequest opWebReq = OptionWebRequest.builder().withValue(VALID_VALUE).build();
	private final OptionWebResponse opWebResp = OptionWebResponse.builder().withValue(opWebReq.getValue()).build();
	private final QuestionWebRequest qstWebReq = QuestionWebRequest.builder()
																	.withAnswer(opWebReq)
																	.withOptions(Arrays.asList(opWebReq))
																	.withQuestion(Q1)
																	.build();
	private final QuestionWebResponse qstWebResp = QuestionWebResponse.builder()
																		.withAnswer(opWebResp)
																		.withOptions(Arrays.asList(opWebResp))
																		.withQuestion(Q1)
																		.build();
	
	
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(qstWebResp)).when(this.manager).findAll();
		List<QuestionWebResponse> actual = this.controller.findAll().getBody();
		MatcherAssert.assertThat(actual, Matchers.hasItems(Matchers.is(qstWebResp)));
	}
	
	@Test
	public void givenExistentDocumentShouldFindByIdOk() {
		Mockito.doReturn(qstWebResp).when(this.manager).findById(ArgumentMatchers.eq(VALID_ID));
		QuestionWebResponse actual = this.controller.findById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(qstWebResp));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Mockito.doReturn(Boolean.TRUE).when(this.manager).deleteById(ArgumentMatchers.eq(VALID_ID));
		Boolean actual = this.controller.deleteById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(qstWebResp).when(this.manager).create(ArgumentMatchers.eq(qstWebReq));
		QuestionWebResponse actual = this.controller.create(qstWebReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(qstWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		QuestionWebRequest updatedReq = qstWebReq.cloneBuilder().withQuestion(UPD_Q1).build();
		QuestionWebResponse updatedResp = qstWebResp.cloneBuilder().withQuestion(UPD_Q1).build();
		Mockito.doReturn(updatedResp).when(this.manager).create(ArgumentMatchers.eq(updatedReq));
		QuestionWebResponse actual = this.controller.create(updatedReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(updatedResp));
	}
}
