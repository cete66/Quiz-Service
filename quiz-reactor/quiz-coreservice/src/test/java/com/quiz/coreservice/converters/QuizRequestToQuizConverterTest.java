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
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Quiz;

public class QuizRequestToQuizConverterTest {

	private static final String ID = "1";
	
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private static final String QZ_ID1 = "qz_id1";
	private static final String QZ_NAME1 = "qz_name1";
	private final QuizRequest toConvert = initToConvertQuiz();
	private final OptionRequestToOptionConverter optConv = 
			new OptionRequestToOptionConverter();
	private final QuestionRequestToQuestionConverter converter = 
			new QuestionRequestToQuestionConverter(optConv);
	private final QuizRequestToQuizConverter quizConv =
			new QuizRequestToQuizConverter(converter);

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.quizConv.convert(null));
	}

	

	@Test
	public void givenValidParameterShouldConvertProperly() {
		Quiz expected = initExpectedQuiz();
		MatcherAssert.assertThat(this.quizConv.convert(toConvert), Matchers.is(expected));
	}

	private List<OptionRequest> initToConvertOptsList() {

		List<OptionRequest> result = new ArrayList<OptionRequest>();

		result.add(OptionRequest.builder().withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<Option> initExpectedOptsList() {

		List<Option> result = new ArrayList<Option>();

		result.add(Option.builder().withId(ID).withValue(VALUE).build());

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
	
	private QuizRequest initToConvertQuiz() {
		return QuizRequest.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initToConvert()))
				.build();
	}
	
	private Quiz initExpectedQuiz() {
		return Quiz.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initExpected()))
				.build();
	}
}
