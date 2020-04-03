package com.quiz.response;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;

public class OptionWebResponseTest extends AbstractModelBeanTest<OptionWebResponse> {

	private final OptionWebResponseBuilder optBuilder = OptionWebResponse.builder()
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
