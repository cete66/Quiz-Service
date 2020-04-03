package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;

public class QuestionToQuestionResponseConverterTest {

	private static final String ID = "1";
	
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private final Question toConvert = initToConvert();
	private final QuestionToQuestionResponseConverter converter = 
			new QuestionToQuestionResponseConverter(
			new OptionToOptionResponseConverter());

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}

	@Test
	public void givenValidParameterShouldConvertProperly() {
		QuestionResponse expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private List<Option> initToConvertOptsList() {

		List<Option> result = new ArrayList<Option>();

		result.add(Option.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<OptionResponse> initExpectedOptsList() {

		List<OptionResponse> result = new ArrayList<OptionResponse>();

		result.add(OptionResponse.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private QuestionResponse initExpected() {
		return QuestionResponse.builder()
				.withAnswer(initExpectedOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initExpectedOptsList())
				.withQuestion(Q1)
				.build();
	}

	private Question initToConvert() {
		return Question.builder()
				.withAnswer(initToConvertOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initToConvertOptsList())
				.withQuestion(Q1)
				.build();
	}
}
