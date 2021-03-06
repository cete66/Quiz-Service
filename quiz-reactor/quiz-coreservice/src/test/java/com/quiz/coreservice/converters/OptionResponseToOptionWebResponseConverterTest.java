package com.quiz.coreservice.converters;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.response.OptionWebResponse;

public class OptionResponseToOptionWebResponseConverterTest {

	private static final String ID = "1";
	
	private static final String VALUE = "v";
	private final OptionResponse toConvert = initToConvert();
	private final OptionResponseToOptionWebResponseConverter converter = 
			new OptionResponseToOptionWebResponseConverter();
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.converter.convert(null));
	}
	
	
	@Test
	public void givenValidParameterShouldConvertProperly() {
		OptionWebResponse expected = initExpected();
		MatcherAssert.assertThat(this.converter.convert(toConvert), Matchers.is(expected));
	}

	private OptionWebResponse initExpected() {
		return OptionWebResponse.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
	
	private OptionResponse initToConvert() {
		return OptionResponse.builder()
				.withId(ID)
				.withValue(VALUE)
				.build();
	}
}
