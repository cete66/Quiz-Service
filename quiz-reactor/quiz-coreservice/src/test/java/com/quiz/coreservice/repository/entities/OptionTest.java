package com.quiz.coreservice.repository.entities;

import org.junit.jupiter.api.BeforeEach;

import com.bank.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Option.OptionBuilder;

public class OptionTest extends AbstractModelBeanTest<Option>{

	private final OptionBuilder builder = Option.builder()
										.withCorrect(true)
										.withId("1")
										.withValue("v");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = builder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withCorrect(false).build();
	}
}
