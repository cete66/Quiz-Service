package com.quiz.coreservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.test.context.TestPropertySource;

import com.quiz.coreservice.converters.OptionRequestToOptionConverter;
import com.quiz.coreservice.converters.OptionToOptionResponseConverter;
import com.quiz.coreservice.converters.QuestionRequestToQuestionConverter;
import com.quiz.coreservice.converters.QuestionToQuestionResponseConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.QuestionRepository;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;

@TestPropertySource("classpath:application.properties")
public class QuestionServiceImplTest {

	private Converter<Option, OptionResponse> optFromCoreConverter;
	private Converter<OptionRequest, Option> optToCoreConverter;
	private Converter<Question, QuestionResponse> qstFromCoreConverter;
	private Converter<QuestionRequest, Question> qstToCoreConverter;
	@Mock
	private QuestionRepository repository;
	private QuestionServiceImpl service;
	private String errorUpdatingEntity;
	private OptionRequest data = OptionRequest.builder().withId("1").withValue("v").build();
	private Option coreData;
	private QuestionRequest qstReqData = QuestionRequest.builder().withAnswer(data).withId("1").withQuestion("q1").withOptions(Arrays.asList(data)).build();
	private Question qstCoreData;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		optFromCoreConverter = new OptionToOptionResponseConverter();
		optToCoreConverter = new OptionRequestToOptionConverter();
		qstFromCoreConverter = new QuestionToQuestionResponseConverter(optFromCoreConverter);
		qstToCoreConverter = new QuestionRequestToQuestionConverter(optToCoreConverter);
		this.errorUpdatingEntity = "error updating";
		coreData = optToCoreConverter.convert(data);
		this.service = new QuestionServiceImpl(repository, qstFromCoreConverter, qstToCoreConverter, errorUpdatingEntity);
		qstCoreData = qstToCoreConverter.convert(qstReqData);
	}
	
	@Test
	public void givenExistentDocumentsShouldFindAllReturnDocuments() {
		Mockito.doReturn(qstCoreData).when(this.repository).insert(ArgumentMatchers.eq(qstCoreData));
		Mockito.doReturn(Arrays.asList(qstCoreData)).when(this.repository).findAll();
		QuestionResponse expected = this.service.create(qstReqData);
		List<QuestionResponse> actual = this.service.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
		
	}
	
	@Test
	public void givenExistentDocumentShouldFindByIdReturnDocument() {
		Optional<Question> optCoreData = Optional.ofNullable(qstCoreData);
		Mockito.doReturn(qstCoreData).when(this.repository).insert(ArgumentMatchers.eq(qstCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreData.getId()));
		QuestionResponse expected = this.service.create(qstReqData);
		QuestionResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Optional<Question> optCoreData = Optional.ofNullable(qstCoreData);
		Mockito.doReturn(qstCoreData).when(this.repository).insert(ArgumentMatchers.eq(qstCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(qstCoreData.getId()));
		QuestionResponse expected = this.service.create(qstReqData);
		QuestionResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Optional<Question> optCoreData = Optional.ofNullable(null);
		Mockito.doReturn(qstCoreData).when(this.repository).insert(ArgumentMatchers.eq(qstCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(qstCoreData.getId()));
		QuestionResponse expected = this.service.create(qstReqData);
		Integer actual = this.service.deleteById(expected.getId());
		
		MatcherAssert.assertThat(actual, Matchers.is(Integer.valueOf(1)));
		Mockito.verify(this.repository).deleteById(ArgumentMatchers.eq(qstCoreData.getId()));
	}
	
	@Test
	public void givenExistentDocumentShouldUpdateOk() {
		QuestionRequest dataUpdt = qstReqData.cloneBuilder().withAnswer(data.cloneBuilder().withValue("asdasdsdasd").build()).build();
		Question coreUpdt = qstToCoreConverter.convert(dataUpdt);
		Optional<Question> optCoreData = Optional.ofNullable(coreUpdt);
		Mockito.doReturn(optCoreData).when(this.repository).findOne(ArgumentMatchers.eq(Example.of(coreUpdt)));
		Mockito.doReturn(coreUpdt).when(this.repository).save(ArgumentMatchers.eq(coreUpdt));
		QuestionResponse actualUpdt = this.service.update(dataUpdt);
		QuestionResponse exptUpd = this.qstFromCoreConverter.convert(coreUpdt);
		MatcherAssert.assertThat(actualUpdt, Matchers.is(exptUpd));
	}
}
