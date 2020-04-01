package com.quiz.framework.converter;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListConverterTest {

	private static final Integer v1 = 1;
	private final ListConverter<String, Integer> listconv = initListConverter();
	
	
	@Test
	public void givenNullParameterShouldReturnNull() {
		Assertions.assertNull(this.listconv.convert(null));
	}
	
	@Test
	public void givenValidParameterShouldConvertproperly() {
		List<Integer> expected = Arrays.asList(v1);
		MatcherAssert.assertThat(listconv.convert(Arrays.asList(v1.toString())), Matchers.contains(expected));
	}

	
	
	private ListConverter<String, Integer> initListConverter() {
		Converter<String, Integer> conv = new Converter<String, Integer>() {
			
			@Override
			public Integer convert(String object) {

				if (object == null) {
					return null;
				}
				return Integer.valueOf(object);
			}
		};
		return new ListConverter<String, Integer>(conv);
	}
}
