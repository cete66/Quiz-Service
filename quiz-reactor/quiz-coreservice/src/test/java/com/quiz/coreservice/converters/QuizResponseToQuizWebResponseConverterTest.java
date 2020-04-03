package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

public class QuizResponseToQuizWebResponseConverterTest {

	private static final String ID = "1";
	
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private static final String QZ_ID1 = "qz_id1";
	private static final String QZ_NAME1 = "qz_name1";
	private final QuizResponse toConvert = initToConvertQuiz();
	private final OptionResponseToOptionWebResponseConverter optConv = 
			new OptionResponseToOptionWebResponseConverter();
	private final QuestionResponseToQuestionWebResponseConverter converter = 
			new QuestionResponseToQuestionWebResponseConverter(optConv);
	private final QuizResponseToQuizWebResponseConverter quizConv =
			new QuizResponseToQuizWebResponseConverter(converter);

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.quizConv.convert(null));
	}

	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		QuizWebResponse expected = initExpectedQuiz();
		MatcherAssert.assertThat(this.quizConv.convert(toConvert), Matchers.is(expected));
	}

	private List<OptionResponse> initToConvertOptsList() {

		List<OptionResponse> result = new ArrayList<OptionResponse>();

		result.add(OptionResponse.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<OptionWebResponse> initExpectedOptsList() {

		List<OptionWebResponse> result = new ArrayList<OptionWebResponse>();

		result.add(OptionWebResponse.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private QuestionWebResponse initExpected() {
		return QuestionWebResponse.builder()
				.withAnswer(initExpectedOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initExpectedOptsList())
				.withQuestion(Q1)
				.build();
	}

	private QuestionResponse initToConvert() {
		return QuestionResponse.builder()
				.withAnswer(initToConvertOptsList().get(0))
				.withId(QT1_ID)
				.withOptions(initToConvertOptsList())
				.withQuestion(Q1)
				.build();
	}
	
	private QuizResponse initToConvertQuiz() {
		return QuizResponse.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initToConvert()))
				.build();
	}
	
	private QuizWebResponse initExpectedQuiz() {
		return QuizWebResponse.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initExpected()))
				.build();
	}
}
