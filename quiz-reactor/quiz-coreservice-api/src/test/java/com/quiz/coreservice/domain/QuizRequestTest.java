package com.quiz.coreservice.domain;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.domain.OptionRequest.OptionRequestBuilder;
import com.quiz.coreservice.domain.QuestionRequest.QuestionRequestBuilder;
import com.quiz.coreservice.domain.QuizRequest.QuizRequestBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class QuizRequestTest extends AbstractModelBeanTest<QuizRequest>{

	
	private final OptionRequestBuilder builder = OptionRequest.builder()
			.withCorrect(true)
			.withId("1")
			.withValue("v");
	private final QuestionRequestBuilder qBuilder = QuestionRequest.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.withCorrect(false).build()))
			.withQuestion("a");
	private final QuizRequestBuilder quizBuilder = QuizRequest.builder()
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
