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
import com.quiz.coreservice.converters.QuizRequestToQuizConverter;
import com.quiz.coreservice.converters.QuizToQuizResponseConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.repository.QuizRepository;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;

@TestPropertySource("classpath:application.properties")
public class QuizServiceImplTest {

	private Converter<Option, OptionResponse> optFromCoreConverter;
	private Converter<OptionRequest, Option> optToCoreConverter;
	private Converter<Question, QuestionResponse> qstFromCoreConverter;
	private Converter<QuestionRequest, Question> qstToCoreConverter;
	private Converter<Quiz, QuizResponse> qzFromCoreConverter;
	private Converter<QuizRequest, Quiz> qzToCoreConverter;
	@Mock
	private QuizRepository repository;
	private QuizServiceImpl service;
	private String errorUpdatingEntity;
	private OptionRequest data = OptionRequest.builder().withId("1").withValue("v").build();
	private Option coreData;
	private QuestionRequest qstReqData = QuestionRequest.builder().withAnswer(data).withId("1").withQuestion("q1").withOptions(Arrays.asList(data)).build();
	private QuizRequest qzReqData = QuizRequest.builder().withId("1").withName("quiz1").withQuestions(Arrays.asList(qstReqData)).build();
	private Quiz qzCoreData;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		optFromCoreConverter = new OptionToOptionResponseConverter();
		optToCoreConverter = new OptionRequestToOptionConverter();
		qstFromCoreConverter = new QuestionToQuestionResponseConverter(optFromCoreConverter);
		qstToCoreConverter = new QuestionRequestToQuestionConverter(optToCoreConverter);
		qzFromCoreConverter = new QuizToQuizResponseConverter(qstFromCoreConverter);
		qzToCoreConverter = new QuizRequestToQuizConverter(qstToCoreConverter);
		this.errorUpdatingEntity = "error updating";
		coreData = optToCoreConverter.convert(data);
		qzCoreData = qzToCoreConverter.convert(qzReqData);
		this.service = new QuizServiceImpl(repository, qzFromCoreConverter, qzToCoreConverter, errorUpdatingEntity);
	}
	
	@Test
	public void givenExistentDocumentsShouldFindAllReturnDocuments() {
		Mockito.doReturn(qzCoreData).when(this.repository).insert(ArgumentMatchers.eq(qzCoreData));
		Mockito.doReturn(Arrays.asList(qzCoreData)).when(this.repository).findAll();
		QuizResponse expected = this.service.create(qzReqData);
		List<QuizResponse> actual = this.service.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
		
	}
	
	@Test
	public void givenExistentDocumentShouldFindByIdReturnDocument() {
		Optional<Quiz> optCoreData = Optional.ofNullable(qzCoreData);
		Mockito.doReturn(qzCoreData).when(this.repository).insert(ArgumentMatchers.eq(qzCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreData.getId()));
		QuizResponse expected = this.service.create(qzReqData);
		QuizResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Optional<Quiz> optCoreData = Optional.ofNullable(qzCoreData);
		Mockito.doReturn(qzCoreData).when(this.repository).insert(ArgumentMatchers.eq(qzCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(qzCoreData.getId()));
		QuizResponse expected = this.service.create(qzReqData);
		QuizResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Optional<Quiz> optCoreData = Optional.ofNullable(null);
		Mockito.doReturn(qzCoreData).when(this.repository).insert(ArgumentMatchers.eq(qzCoreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(qzCoreData.getId()));
		QuizResponse expected = this.service.create(qzReqData);
		Integer actual = this.service.deleteById(expected.getId());
		
		MatcherAssert.assertThat(actual, Matchers.is(Integer.valueOf(1)));
		Mockito.verify(this.repository).deleteById(ArgumentMatchers.eq(qzCoreData.getId()));
	}
	
	@Test
	public void givenExistentDocumentShouldUpdateOk() {
		QuizRequest dataUpdt = qzReqData.cloneBuilder().withName("dkj").build();
		Quiz coreUpdt = qzToCoreConverter.convert(dataUpdt);
		Optional<Quiz> optCoreData = Optional.ofNullable(coreUpdt);
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreUpdt.getId()));
		Mockito.doReturn(coreUpdt).when(this.repository).save(ArgumentMatchers.eq(coreUpdt));
		QuizResponse actualUpdt = this.service.update(dataUpdt, dataUpdt.getId());
		QuizResponse exptUpd = this.qzFromCoreConverter.convert(coreUpdt);
		MatcherAssert.assertThat(actualUpdt, Matchers.is(exptUpd));
	}
}
