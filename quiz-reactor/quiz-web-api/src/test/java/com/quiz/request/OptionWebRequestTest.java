package com.quiz.request;

import org.junit.jupiter.api.BeforeEach;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.request.OptionWebRequest.OptionWebRequestBuilder;

public class OptionWebRequestTest extends AbstractModelBeanTest<OptionWebRequest> {

	private final OptionWebRequestBuilder optBuilder = OptionWebRequest.builder()
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
