package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuizWebRequest;

public class QuizWebRequestToQuizRequestConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private static final String QZ_ID1 = "qz_id1";
	private static final String QZ_NAME1 = "qz_name1";
	private final QuizWebRequest toConvert = initToConvertQuiz();
	private final OptionWebRequestToOptionRequestConverter optConv = 
			new OptionWebRequestToOptionRequestConverter();
	private final QuestionWebRequestToQuestionRequestConverter converter = 
			new QuestionWebRequestToQuestionRequestConverter(optConv);
	private final QuizWebRequestToQuizRequestConverter quizConv =
			new QuizWebRequestToQuizRequestConverter(converter);

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.quizConv.convert(null));
	}

	

	@Test
	public void givenValidParameterShouldConvertProperly() {
		QuizRequest expected = initExpectedQuiz();
		MatcherAssert.assertThat(this.quizConv.convert(toConvert), Matchers.is(expected));
	}

	private List<OptionWebRequest> initToConvertOptsList() {

		List<OptionWebRequest> result = new ArrayList<OptionWebRequest>();

		result.add(OptionWebRequest.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<OptionRequest> initExpectedOptsList() {

		List<OptionRequest> result = new ArrayList<OptionRequest>();

		result.add(OptionRequest.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private QuestionRequest initExpected() {
		return QuestionRequest.builder()
				.withAnswer(initExpectedOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initExpectedOptsList())
				.withQuestion(Q1)
				.build();
	}

	private QuestionWebRequest initToConvert() {
		return QuestionWebRequest.builder()
				.withAnswer(initToConvertOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initToConvertOptsList())
				.withQuestion(Q1)
				.build();
	}
	
	private QuizWebRequest initToConvertQuiz() {
		return QuizWebRequest.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initToConvert()))
				.build();
	}
	
	private QuizRequest initExpectedQuiz() {
		return QuizRequest.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initExpected()))
				.build();
	}
}
