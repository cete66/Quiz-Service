package com.quiz.request;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.request.OptionWebRequest.OptionWebRequestBuilder;
import com.quiz.request.QuestionWebRequest.QuestionWebRequestBuilder;
import com.quiz.request.QuizWebRequest.QuizWebRequestBuilder;

public class QuizWebRequestTest extends AbstractModelBeanTest<QuizWebRequest>{

	private final OptionWebRequestBuilder builder = OptionWebRequest.builder()
			.withId("1")
			.withValue("v");
	private final QuestionWebRequestBuilder qBuilder = QuestionWebRequest.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.build()))
			.withQuestion("a");
	private final QuizWebRequestBuilder quizBuilder = QuizWebRequest.builder()
			.withId("1")
			.withName("quiz1")
			.withQuestions(Arrays.asList(qBuilder.build(), qBuilder.withQuestion("b").build()));
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = quizBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withName("quiz2").build();
	}

}
