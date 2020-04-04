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

import com.quiz.coreservice.converters.ListQuizWebResponseToAllQuizWebResponseConverter;
import com.quiz.coreservice.manager.QuestionManagerImpl;
import com.quiz.coreservice.manager.QuizManagerImpl;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

@TestPropertySource(locations = "/application.properties")
public class QuizControllerTest {

	private static final String VALID_ID = "1";
	private static final String VALID_VALUE = "valid";
	private static final String Q1 = "q1";
	private static final String UPD_Q1 = "updQ1";
	private static final String VALID_QUIZ = "quiz1";
	private static final String UPD_QZ = null;
	private static final String TAG_NAME = "q";
	@Mock
	private QuizManagerImpl manager;
	@InjectMocks
	private QuizController controller;
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
	private final QuizWebRequest qzWebReq = QuizWebRequest.builder()
															.withName(VALID_QUIZ)
															.withQuestions(Arrays.asList(qstWebReq))
															.build();
	private final QuizWebResponse qzWebResp = QuizWebResponse.builder()
															.withName(VALID_QUIZ)
															.withQuestions(Arrays.asList(qstWebResp))
															.build();
	private final ListQuizWebResponseToAllQuizWebResponseConverter converter = new ListQuizWebResponseToAllQuizWebResponseConverter(TAG_NAME);
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(qzWebResp)).when(this.manager).findAll();
		List<QuizWebResponse> actual = this.controller.findAll().getBody();
		MatcherAssert.assertThat(actual, Matchers.hasItems(Matchers.is(qzWebResp)));
	}
	
	@Test
	public void givenExistentDocumentShouldFindByIdOk() {
		Mockito.doReturn(qzWebResp).when(this.manager).findById(ArgumentMatchers.eq(VALID_ID));
		QuizWebResponse actual = this.controller.findById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(qzWebResp));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Mockito.doReturn(Boolean.TRUE).when(this.manager).deleteById(ArgumentMatchers.eq(VALID_ID));
		Boolean actual = this.controller.deleteById(VALID_ID).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(qzWebResp).when(this.manager).create(ArgumentMatchers.eq(qzWebReq));
		QuizWebResponse actual = this.controller.create(qzWebReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(qzWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		QuizWebRequest updatedReq = qzWebReq.cloneBuilder().withName(UPD_QZ).build();
		QuizWebResponse updatedResp = qzWebResp.cloneBuilder().withName(UPD_QZ).build();
		Mockito.doReturn(updatedResp).when(this.manager).create(ArgumentMatchers.eq(updatedReq));
		QuizWebResponse actual = this.controller.create(updatedReq).getBody();
		MatcherAssert.assertThat(actual, Matchers.is(updatedResp));
	}
	
	@Test
	public void givenExistentDocumentsShouldAllQuizReturnAllQuizWebResponse() {
		AllQuizWebResponse allQz = converter.convert(Arrays.asList(qzWebResp));
		Mockito.doReturn(Arrays.asList(qzWebResp)).when(this.manager).findAll();
		Mockito.doReturn(allQz).when(this.manager).generate(Arrays.asList(qzWebResp));
		AllQuizWebResponse actual = this.controller.allQuiz().getBody();
		MatcherAssert.assertThat(actual, Matchers.is(allQz));
	}
	
}
