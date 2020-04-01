package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;

public class QuestionWebRequestToQuestionRequestConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private final QuestionWebRequest toConvert = initToConvert();
	private final QuestionWebRequestToQuestionRequestConverter converter = 
			new QuestionWebRequestToQuestionRequestConverter(
			new OptionWebRequestToOptionRequestConverter());

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}

	@Test
	public void givenValidParameterShouldConvertProperly() {
		QuestionRequest expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private List<OptionWebRequest> initToConvertOptsList() {

		List<OptionWebRequest> result = new ArrayList<OptionWebRequest>();

		result.add(OptionWebRequest.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<OptionRequest> initExpectedOptsList() {

		List<OptionRequest> result = new ArrayList<OptionRequest>();

		result.add(OptionRequest.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

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
}
