package com.quiz.coreservice.repository.entities;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.bank.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Option.OptionBuilder;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Question.QuestionBuilder;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.coreservice.repository.entities.Quiz.QuizBuilder;

public class QuizTest extends AbstractModelBeanTest<Quiz>{

	private final OptionBuilder builder = Option.builder()
			.withCorrect(true)
			.withId("1")
			.withValue("v");
	private final QuestionBuilder qBuilder = Question.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.withCorrect(false).build()))
			.withQuestion("a");
	private final QuizBuilder quizBuilder = Quiz.builder()
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
