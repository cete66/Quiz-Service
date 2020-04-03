package com.quiz.response;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;
import com.quiz.response.QuizWebResponse.QuizWebResponseBuilder;

public class QuizWebResponseTest extends AbstractModelBeanTest<QuizWebResponse>{

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
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = quizBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withName("quiz2").build();
	}

}
