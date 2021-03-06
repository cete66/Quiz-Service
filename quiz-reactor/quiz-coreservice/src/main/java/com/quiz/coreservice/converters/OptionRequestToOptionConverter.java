package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@Component("optionRequestToOptionConverter")
public class OptionRequestToOptionConverter implements Converter<OptionRequest, Option> {

	@Override
	public Option convert(final OptionRequest from) {
		if (from == null) {
			return null;
		}
		return Option.builder()
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
