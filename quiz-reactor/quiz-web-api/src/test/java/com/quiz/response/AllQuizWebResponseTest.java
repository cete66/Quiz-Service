package com.quiz.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.response.AllQuizQuestionWebResponse.AllQuizQuestionWebResponseBuilder;
import com.quiz.response.AllQuizWebResponse.AllQuizWebResponseBuilder;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;
import com.quiz.response.QuizWebResponse.QuizWebResponseBuilder;

public class AllQuizWebResponseTest extends AbstractModelBeanTest<AllQuizWebResponse>{

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
	private final AllQuizWebResponseBuilder allQBuilder = AllQuizWebResponse.builder();
	
	private final AllQuizQuestionWebResponseBuilder allQuizQuestionBuilder = AllQuizQuestionWebResponse.builder()
																						.withAnswer(qBuilder.build().getAnswer().getValue())
																						.withId(qBuilder.build().getId())
																						.withQuestion(qBuilder.build().getQuestion())
																						.withOptions(Arrays.asList(builder.build().getValue(), builder.build().getValue()));
		
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = allQBuilder.withQuiz(generateFinalMap(Arrays.asList(quizBuilder.build()))).build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = allQBuilder.withQuiz(generateFinalMap(Arrays.asList(quizBuilder
										.withName("quiz2").build()))).build();
	}
	
	/**
	 * To avoid coreservice dependency, we need to duplicate 
	 * <code>ListQuizWebResponseToAllQuizWebResponseConverter</code>'s code here
	 * @param quizList
	 * @return
	 */
	private Map<String, Map<String, AllQuizQuestionWebResponse>> 
	generateFinalMap(final List<QuizWebResponse> quizList) {

		Map<String, Map<String, AllQuizQuestionWebResponse>> quizMap = 
				new HashMap<String, Map<String,AllQuizQuestionWebResponse>>();

		quizList.stream().forEach(quiz -> {
			Map<String,AllQuizQuestionWebResponse> qst = new HashMap<String, AllQuizQuestionWebResponse>();
			IntStream.range(0, quiz.getQuestions().size()).forEach(index -> {
				qst.put("q"+(index+1), allQuizQuestionBuilder.build());
			});
			quizMap.put(quiz.getName(), qst);
		});		
		
		return quizMap;
	}

}
