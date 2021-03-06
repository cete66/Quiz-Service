package com.quiz.coreservice.domain;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.quiz.coreservice.domain.QuestionResponse.QuestionResponseBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class QuestionResponseTest extends AbstractModelBeanTest<QuestionResponse>{

	private final QuestionResponseBuilder qtBuilder = QuestionResponse.builder()
			.withAnswer(Mockito.mock(OptionResponse.class))
			.withId("1")
			.withOptions(Arrays.asList(Mockito.mock(OptionResponse.class)))
			.withQuestion("a");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = qtBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withId("2").build();
	}
}
