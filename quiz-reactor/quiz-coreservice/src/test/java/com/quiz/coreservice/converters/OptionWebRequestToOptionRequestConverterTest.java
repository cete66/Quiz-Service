package com.quiz.coreservice.converters;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.request.OptionWebRequest;

public class OptionWebRequestToOptionRequestConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private final OptionWebRequest toConvert = initToConvert();
	private final OptionWebRequestToOptionRequestConverter converter = 
			new OptionWebRequestToOptionRequestConverter();
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}
	
	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		OptionRequest expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private OptionRequest initExpected() {
		return OptionRequest.builder()
				
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
	
	private OptionWebRequest initToConvert() {
		return OptionWebRequest.builder()
				
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
}
