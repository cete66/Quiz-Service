package com.quiz.coreservice.repository.entities;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Option.OptionBuilder;
import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;

public class OptionTest extends AbstractModelBeanTest<Option>{

	private final OptionBuilder builder = Option.builder()
										.withId("1")
										.withValue("v");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = builder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withValue("v2").build();
	}
}
