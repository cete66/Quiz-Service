package com.quiz.coreservice.converters;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.entities.Option;

public class OptionToOptionResponseConverterTest {

	private static final String ID = "1";
	
	private static final String VALUE = "v";
	private final Option toConvert = initToConvert();
	private final OptionToOptionResponseConverter converter = 
			new OptionToOptionResponseConverter();
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}
	
	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		OptionResponse expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private OptionResponse initExpected() {
		return OptionResponse.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
	
	private Option initToConvert() {
		return Option.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
}
