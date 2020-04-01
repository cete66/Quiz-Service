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
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Quiz;

public class QuizToQuizResponseConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private static final String QT1_ID = "qt1";
	private static final String Q1 = "q1";
	private static final String QZ_ID1 = "qz_id1";
	private static final String QZ_NAME1 = "qz_name1";
	private final Quiz toConvert = initToConvertQuiz();
	private final OptionToOptionResponseConverter optConv = 
			new OptionToOptionResponseConverter();
	private final QuestionToQuestionResponseConverter converter = 
			new QuestionToQuestionResponseConverter(optConv);
	private final QuizToQuizResponseConverter quizConv =
			new QuizToQuizResponseConverter(converter);

	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.quizConv.convert(null));
	}

	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		QuizResponse expected = initExpectedQuiz();
		MatcherAssert.assertThat(this.quizConv.convert(toConvert), Matchers.is(expected));
	}

	private List<Option> initToConvertOptsList() {

		List<Option> result = new ArrayList<Option>();

		result.add(Option.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

		return result;
	}

	private List<OptionResponse> initExpectedOptsList() {

		List<OptionResponse> result = new ArrayList<OptionResponse>();

		result.add(OptionResponse.builder().withCorrect(CORRECT).withId(ID).withValue(VALUE).build());

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
	
	private Quiz initToConvertQuiz() {
		return Quiz.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initToConvert()))
				.build();
	}
	
	private QuizResponse initExpectedQuiz() {
		return QuizResponse.builder()
				.withId(QZ_ID1)
				.withName(QZ_NAME1)
				.withQuestions(Arrays.asList(initExpected()))
				.build();
	}
}
