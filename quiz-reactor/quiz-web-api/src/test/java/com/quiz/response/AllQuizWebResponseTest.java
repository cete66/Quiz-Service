package com.quiz.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.response.AllQuizWebResponse.AllQuizWebResponseBuilder;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;
import com.quiz.response.QuizWebResponse.QuizWebResponseBuilder;

public class AllQuizWebResponseTest extends AbstractModelBeanTest<AllQuizWebResponse>{

	private final OptionWebResponseBuilder builder = OptionWebResponse.builder()
			.withCorrect(true)
			.withId("1")
			.withValue("v");
	private final QuestionWebResponseBuilder qBuilder = QuestionWebResponse.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.withCorrect(false).build()))
			.withQuestion("a");
	private final QuizWebResponseBuilder quizBuilder = QuizWebResponse.builder()
			.withId("1")
			.withName("quiz1")
			.withQuestions(Arrays.asList(qBuilder.build(), qBuilder.withQuestion("b").build()));
	private final AllQuizWebResponseBuilder allQBuilder = AllQuizWebResponse.builder();
		
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = allQBuilder.withQuiz(generateFinalMap(Arrays.asList(quizBuilder.build()))).build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = allQBuilder.withQuiz(generateFinalMap(Arrays.asList(quizBuilder
										.withName("quiz2").build()))).build();
	}
	
	//TODO REEMPLEZAR ESTO POR UN FORMATTER O CONVERTER - Robin
	private Map<String, Map<String, QuestionWebResponse>> 
	generateFinalMap(final List<QuizWebResponse> quizList) {

		Map<String, Map<String, QuestionWebResponse>> quiz = 
				new HashMap<String, Map<String,QuestionWebResponse>>();

		quizList.stream().forEach(q -> {
			Map<String,QuestionWebResponse> qst = new HashMap<String, QuestionWebResponse>();
			IntStream.range(0, q.getQuestions().size()).forEach(index -> {
				//TODO REEMPLAZAR LA 'Q' POR UNA PROPERTY - Robin
				qst.put("q"+(index+1), q.getQuestions().get(index));
			});
			quiz.put(q.getName(), qst);
		});		
		
		return quiz;
	}

}
