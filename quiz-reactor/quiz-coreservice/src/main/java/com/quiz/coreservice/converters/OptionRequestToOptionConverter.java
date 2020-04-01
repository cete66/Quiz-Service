package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@Component
public class OptionRequestToOptionConverter implements Converter<OptionRequest, Option> {

	@Override
	public Option convert(OptionRequest from) {
		if (from == null) {
			return null;
		}
		return Option.builder()
				.withCorrect(from.getCorrect())
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
