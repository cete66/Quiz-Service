package com.quiz.coreservice.manager;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.quiz.coreservice.converters.ListQuizWebResponseToAllQuizWebResponseConverter;
import com.quiz.coreservice.converters.OptionResponseToOptionWebResponseConverter;
import com.quiz.coreservice.converters.OptionWebRequestToOptionRequestConverter;
import com.quiz.coreservice.converters.QuestionResponseToQuestionWebResponseConverter;
import com.quiz.coreservice.converters.QuestionWebRequestToQuestionRequestConverter;
import com.quiz.coreservice.converters.QuizResponseToQuizWebResponseConverter;
import com.quiz.coreservice.converters.QuizWebRequestToQuizRequestConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.service.QuizServiceImpl;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListToEntityConverter;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

public class QuizManagerImplTest {

	@Mock
	private QuizServiceImpl service;
	private QuizManagerImpl manager;
	private OptionWebRequest optWebReq = OptionWebRequest.builder().withId("1").withValue("value1").build();
	private OptionResponse optCoreResp = OptionResponse.builder().withId(optWebReq.getId()).withValue(optWebReq.getId()).build();
	private Converter<OptionResponse, OptionWebResponse> optFromCoreConverter = new OptionResponseToOptionWebResponseConverter();
	private Converter<OptionWebRequest, OptionRequest> optToCoreConverter = new OptionWebRequestToOptionRequestConverter();
	private Converter<QuestionResponse, QuestionWebResponse> qstFromCoreConverter = new QuestionResponseToQuestionWebResponseConverter(optFromCoreConverter);
	private Converter<QuestionWebRequest, QuestionRequest> qstToCoreConverter = new QuestionWebRequestToQuestionRequestConverter(optToCoreConverter);
	private Converter<QuizResponse, QuizWebResponse> qzFromCoreConverter = new QuizResponseToQuizWebResponseConverter(qstFromCoreConverter);
	private Converter<QuizWebRequest, QuizRequest> qzToCoreConverter = new QuizWebRequestToQuizRequestConverter(qstToCoreConverter);
	private ListToEntityConverter<QuizWebResponse, AllQuizWebResponse> allQuizConverter = new ListQuizWebResponseToAllQuizWebResponseConverter("q");
	private QuestionWebRequest qstWebReq = QuestionWebRequest.builder()
																.withId("1")
																.withAnswer(optWebReq)
																.withOptions(Arrays.asList(optWebReq))
																.withQuestion("q1")
																.build();
	private QuestionResponse qstCoreResp = QuestionResponse.builder()
															.withId(qstWebReq.getId())
															.withAnswer(optCoreResp)
															.withOptions(Arrays.asList(optCoreResp))
															.withQuestion(qstWebReq.getQuestion())
															.build();
	private QuizWebResponse qzWebResp;
	private QuizWebRequest qzWebReq = QuizWebRequest.builder().withId("1").withName("quiz1").withQuestions(Arrays.asList(qstWebReq)).build();
	private QuizRequest qzCoreReq;
	private QuizResponse qzCoreResp = QuizResponse.builder().withId(qzWebReq.getId()).withName(qzWebReq.getName()).withQuestions(Arrays.asList(qstCoreResp)).build();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		qzCoreReq = qzToCoreConverter.convert(qzWebReq);
		qzWebResp = qzFromCoreConverter.convert(qzCoreResp);
		this.manager = new QuizManagerImpl(service, qzFromCoreConverter, qzToCoreConverter, allQuizConverter);
	}
	
	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(qzCoreResp)).when(this.service).findAll();
		List<QuizWebResponse> expected = Arrays.asList(qzWebResp);
		List<QuizWebResponse> actual = this.manager.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected.get(0))));
	}
	
	@Test
	public void givenDocumentIdShouldFindByIdReturnDocument() {
		Mockito.doReturn(qzCoreResp).when(this.service).findById(ArgumentMatchers.eq(qzCoreReq.getId()));
		QuizWebResponse actual = this.manager.findById(qzWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(qzWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(qzCoreResp).when(this.service).create(ArgumentMatchers.eq(qzCoreReq));
		QuizWebResponse actual = this.manager.create(qzWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(qzWebResp));
	}
	
	@Test
	public void givenValidDocumentIdShouldDeleteOk() {
		Mockito.doReturn(1).when(this.service).deleteById(ArgumentMatchers.eq(qzCoreReq.getId()));
		Boolean actual = this.manager.deleteById(qzWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Mockito.doReturn(qzCoreResp).when(this.service).update(ArgumentMatchers.eq(qzCoreReq));
		QuizWebResponse actual = this.manager.update(qzWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(qzWebResp));
	}
	
	@Test
	public void givenDocumentsShouldGenerateAllQuizWebResponseOk() {
		Mockito.doReturn(Arrays.asList(qzCoreResp)).when(this.service).findAll();
		List<QuizWebResponse> expected = Arrays.asList(qzWebResp);
		List<QuizWebResponse> actual = this.manager.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected.get(0))));
		Assertions.assertNotNull(this.manager.generate(actual));
	}
}
