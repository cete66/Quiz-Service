package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;

public class QuestionRequestToQuestionConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private final QuestionRequest toConvert = initToConvert();
	private final QuestionRequestToQuestionConverter converter = 
			new QuestionRequestToQuestionConverter(
			new OptionRequestToOptionConverter());

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}

	@Test
	public void givenValidParameterShouldConvertProperly() {
		Question expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private List<OptionRequest> initToConvertOptsList() {

		List<OptionRequest> result = new ArrayList<OptionRequest>();

		result.add(OptionRequest.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<Option> initExpectedOptsList() {

		List<Option> result = new ArrayList<Option>();

		result.add(Option.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

		return result;
	}

	private Question initExpected() {
		return Question.builder()
				.withAnswer(initExpectedOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initExpectedOptsList())
				.withQuestion(Q1)
				.build();
	}

	private QuestionRequest initToConvert() {
		return QuestionRequest.builder()
				.withAnswer(initToConvertOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initToConvertOptsList())
				.withQuestion(Q1)
				.build();
	}
}
