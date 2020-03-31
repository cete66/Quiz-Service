package com.quiz.response;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.quiz.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;

public class QuestionWebResponseTest extends AbstractModelBeanTest<QuestionWebResponse>{

	private final QuestionWebResponseBuilder qtBuilder = QuestionWebResponse.builder()
			.withAnswer(Mockito.mock(OptionWebResponse.class))
			.withId("1")
			.withOptions(Arrays.asList(Mockito.mock(OptionWebResponse.class)))
			.withQuestion("a");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = qtBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withId("2").build();
	}

}
