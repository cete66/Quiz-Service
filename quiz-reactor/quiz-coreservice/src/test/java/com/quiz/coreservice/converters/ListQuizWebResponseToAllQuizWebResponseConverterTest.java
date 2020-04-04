package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import com.quiz.response.AllQuizQuestionWebResponse;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;
import com.quiz.response.QuizWebResponse;
import com.quiz.response.QuizWebResponse.QuizWebResponseBuilder;

@TestPropertySource("classpath:application.properties")
public class ListQuizWebResponseToAllQuizWebResponseConverterTest {

	@Value("${com.quiz.coreservice.allQuizWebResponse.questionsTag.name}")
	private String qstTagName;
	private QuestionWebResponseToAllQuizQuestionWebResponseConverter questionsConverter = 
			new QuestionWebResponseToAllQuizQuestionWebResponseConverter();
	
	private ListQuizWebResponseToAllQuizWebResponseConverter converter;
	private final OptionWebResponseBuilder builder = OptionWebResponse.builder()
			.withId("1")
			.withValue("v");
	private final QuestionWebResponseBuilder qBuilder = QuestionWebResponse.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.build()))
			.withQuestion("a");
	private final QuizWebResponseBuilder quizBuilder = QuizWebResponse.builder()
			.withId("1")
			.withName("quiz1")
			.withQuestions(Arrays.asList(qBuilder.build(), qBuilder.withQuestion("b").build()));
	
	@BeforeEach
	public void setUp() {
		this.qstTagName = "q";
		this.converter = new ListQuizWebResponseToAllQuizWebResponseConverter(qstTagName, questionsConverter);
		
	}
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}
	
	@Test
	public void givenEmptyListShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(new ArrayList<QuizWebResponse>()));
	}
	
	@Test
	public void givenValidListShouldConvertProperly() {
		AllQuizWebResponse expected = initExpected();
		List<QuizWebResponse> toConvert = initToConvert();
		
		AllQuizWebResponse actual = converter.convert(toConvert);
		
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}

	private List<QuizWebResponse> initToConvert() {
		return Arrays.asList(quizBuilder.build(), quizBuilder.build().cloneBuilder().withName("quiz2").build());
	}

	private Map<String, Map<String, AllQuizQuestionWebResponse>> 
	generateMap(List<QuizWebResponse> list) {
		Map<String, Map<String, AllQuizQuestionWebResponse>> quizMap = 
				new HashMap<String, Map<String,AllQuizQuestionWebResponse>>();

		list.stream().forEach(quiz -> {
			Map<String,AllQuizQuestionWebResponse> qst = 
					new HashMap<String, AllQuizQuestionWebResponse>();
			IntStream.range(0, quiz.getQuestions().size()).forEach(index -> {
				qst.put(qstTagName+(index+1), questionsConverter.convert(quiz.getQuestions().get(index)));
			});
			quizMap.put(quiz.getName(), qst);
		});
		return quizMap;
	}

	private AllQuizWebResponse initExpected() {
		return AllQuizWebResponse.builder()
				.withQuiz(generateMap(Arrays.asList(quizBuilder.build(), quizBuilder.build().cloneBuilder().withName("quiz2").build())))
				.build();
	}
}
