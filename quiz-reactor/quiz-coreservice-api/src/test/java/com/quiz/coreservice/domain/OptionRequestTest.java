package com.quiz.coreservice.domain;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.domain.OptionRequest.OptionRequestBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class OptionRequestTest extends AbstractModelBeanTest<OptionRequest>{

	private final OptionRequestBuilder optBuilder = OptionRequest.builder()
			.withId("1")
			.withValue("v");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = optBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withId("2").build();
	}

}
