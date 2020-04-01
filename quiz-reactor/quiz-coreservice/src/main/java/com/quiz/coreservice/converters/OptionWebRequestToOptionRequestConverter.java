package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;

@Component
public class OptionWebRequestToOptionRequestConverter implements Converter<OptionWebRequest, OptionRequest>{

	@Override
	public OptionRequest convert(OptionWebRequest from) {
		if (from == null) {
			return null;
		}
		return OptionRequest.builder()
				.withCorrect(from.getCorrect())
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}