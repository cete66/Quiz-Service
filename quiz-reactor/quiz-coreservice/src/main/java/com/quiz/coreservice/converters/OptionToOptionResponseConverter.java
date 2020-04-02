package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@Component
public class OptionToOptionResponseConverter 
implements Converter<Option, OptionResponse> {

	@Override
	public OptionResponse convert(final Option from) {
		if (from == null) {
			return null;
		}
		return OptionResponse.builder()
				.withCorrect(from.getCorrect())
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
