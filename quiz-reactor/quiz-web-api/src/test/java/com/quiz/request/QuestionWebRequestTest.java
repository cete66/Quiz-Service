package com.quiz.request;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.bank.framework.domain.test.utils.AbstractModelBeanTest;
import com.quiz.request.QuestionWebRequest.QuestionWebRequestBuilder;

public class QuestionWebRequestTest extends AbstractModelBeanTest<QuestionWebRequest>{

	private final QuestionWebRequestBuilder qtBuilder = QuestionWebRequest.builder()
			.withAnswer(Mockito.mock(OptionWebRequest.class))
			.withId("1")
			.withOptions(Arrays.asList(Mockito.mock(OptionWebRequest.class)))
			.withQuestion("a");
	
	@Override
	@BeforeEach
	public void initEntities() {
		entityA1 = qtBuilder.build();
		entityA2 = entityA1.cloneBuilder().build();
		entityB = entityA1.cloneBuilder().withId("2").build();
	}

}
