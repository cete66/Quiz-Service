package com.quiz.coreservice.repository.entities;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Option.OptionBuilder;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Question.QuestionBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class QuestionTest extends AbstractModelBeanTest<Question>{

	private final OptionBuilder builder = Option.builder()
			.withCorrect(true)
			.withId("1")
			.withValue("v");
	private final QuestionBuilder qBuilder = Question.builder()
			.withAnswer(builder.build())
			.withId("1")
			.withOptions(Arrays.asList(builder.build(), builder.withCorrect(false).build()))
			.withQuestion("a");
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = qBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withQuestion("b").build();
	}

}
