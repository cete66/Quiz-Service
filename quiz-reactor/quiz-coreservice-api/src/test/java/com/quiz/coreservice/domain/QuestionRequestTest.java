package com.quiz.coreservice.domain;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.bank.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.coreservice.domain.QuestionRequest.QuestionRequestBuilder;

public class QuestionRequestTest extends AbstractModelBeanTest<QuestionRequest>{

	private final QuestionRequestBuilder qtBuilder = QuestionRequest.builder()
			.withAnswer(Mockito.mock(OptionRequest.class))
			.withId("1")
			.withOptions(Arrays.asList(Mockito.mock(OptionRequest.class)))
			.withQuestion("a");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = qtBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withId("2").build();
	}
}
