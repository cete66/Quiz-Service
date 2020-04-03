package com.quiz.coreservice.repository.entities;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.repository.entities.Option.OptionBuilder;
import com.quiz.coreservice.repository.entities.Question.QuestionBuilder;
import com.quiz.coreservice.repository.entities.Quiz.QuizBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class QuizTest extends AbstractModelBeanTest<Quiz>{

	private final OptionBuilder builder = Option.builder()
			.withId("1")
			.withValue("v");
	private final QuestionBuilder qBuilder = Question.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.build().cloneBuilder().build()))
			.withQuestion("a");
	private final QuizBuilder quizBuilder = Quiz.builder()
			.withId("1")
			.withName("quiz1")
			.withQuestions(Arrays.asList(qBuilder.build(), qBuilder.build().cloneBuilder().withQuestion("b").build()));
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = quizBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withName("quiz2").build();
	}

}
