package com.quiz.coreservice.manager;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.quiz.coreservice.converters.OptionResponseToOptionWebResponseConverter;
import com.quiz.coreservice.converters.OptionWebRequestToOptionRequestConverter;
import com.quiz.coreservice.converters.QuestionResponseToQuestionWebResponseConverter;
import com.quiz.coreservice.converters.QuestionWebRequestToQuestionRequestConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.service.QuestionServiceImpl;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;

public class QuestionManagerImplTest {

	@Mock
	private QuestionServiceImpl service;
	private QuestionManagerImpl manager;
	private OptionWebRequest optWebReq = OptionWebRequest.builder().withId("1").withValue("value1").build();
	private OptionResponse optCoreResp = OptionResponse.builder().withId(optWebReq.getId()).withValue(optWebReq.getId()).build();
	private Converter<OptionResponse, OptionWebResponse> optFromCoreConverter = new OptionResponseToOptionWebResponseConverter();
	private Converter<OptionWebRequest, OptionRequest> optToCoreConverter = new OptionWebRequestToOptionRequestConverter();
	private Converter<QuestionResponse, QuestionWebResponse> qstFromCoreConverter = new QuestionResponseToQuestionWebResponseConverter(optFromCoreConverter);
	private Converter<QuestionWebRequest, QuestionRequest> qstToCoreConverter = new QuestionWebRequestToQuestionRequestConverter(optToCoreConverter);
	private QuestionWebResponse qstWebResp;
	private QuestionWebRequest qstWebReq = QuestionWebRequest.builder()
																.withId("1")
																.withAnswer(optWebReq)
																.withOptions(Arrays.asList(optWebReq))
																.withQuestion("q1")
																.build();
	private QuestionRequest qstCoreReq;
	private QuestionResponse qstCoreResp = QuestionResponse.builder()
															.withId(qstWebReq.getId())
															.withAnswer(optCoreResp)
															.withOptions(Arrays.asList(optCoreResp))
															.withQuestion(qstWebReq.getQuestion())
															.build();
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		qstCoreReq = qstToCoreConverter.convert(qstWebReq);
		qstWebResp = qstFromCoreConverter.convert(qstCoreResp);
		this.manager = new QuestionManagerImpl(service, qstFromCoreConverter, qstToCoreConverter);
	}
	
	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(qstCoreResp)).when(this.service).findAll();
		List<QuestionWebResponse> expected = Arrays.asList(qstWebResp);
		List<QuestionWebResponse> actual = this.manager.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected.get(0))));
	}
	
	@Test
	public void givenDocumentIdShouldFindByIdReturnDocument() {
		Mockito.doReturn(qstCoreResp).when(this.service).findById(ArgumentMatchers.eq(qstCoreReq.getId()));
		QuestionWebResponse actual = this.manager.findById(qstWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(qstWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(qstCoreResp).when(this.service).create(ArgumentMatchers.eq(qstCoreReq));
		QuestionWebResponse actual = this.manager.create(qstWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(qstWebResp));
	}
	
	@Test
	public void givenValidDocumentIdShouldDeleteOk() {
		Mockito.doReturn(1).when(this.service).deleteById(ArgumentMatchers.eq(qstCoreReq.getId()));
		Boolean actual = this.manager.deleteById(qstWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Mockito.doReturn(qstCoreResp).when(this.service).update(ArgumentMatchers.eq(qstCoreReq));
		QuestionWebResponse actual = this.manager.update(qstWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(qstWebResp));
	}
}
