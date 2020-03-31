package com.quiz.coreservice.domain;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.domain.OptionResponse.OptionResponseBuilder;
import com.quiz.coreservice.domain.QuestionResponse.QuestionResponseBuilder;
import com.quiz.coreservice.domain.QuizResponse.QuizResponseBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class QuizResponseTest extends AbstractModelBeanTest<QuizResponse>{

	
	private final OptionResponseBuilder builder = OptionResponse.builder()
			.withCorrect(true)
			.withId("1")
			.withValue("v");
	private final QuestionResponseBuilder qBuilder = QuestionResponse.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.withCorrect(false).build()))
			.withQuestion("a");
	private final QuizResponseBuilder quizBuilder = QuizResponse.builder()
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
