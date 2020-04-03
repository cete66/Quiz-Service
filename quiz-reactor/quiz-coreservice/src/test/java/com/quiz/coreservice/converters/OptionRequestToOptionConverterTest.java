package com.quiz.coreservice.converters;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.request.OptionWebRequest;

public class OptionRequestToOptionConverterTest {

	private static final String ID = "1";
	private static final Boolean CORRECT = true;
	private static final String VALUE = "v";
	private final OptionRequest toConvert = initToConvert();
	private final OptionRequestToOptionConverter converter = 
			new OptionRequestToOptionConverter();
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}
	
	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		Option expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private Option initExpected() {
		return Option.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
	
	private OptionRequest initToConvert() {
		return OptionRequest.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
}
