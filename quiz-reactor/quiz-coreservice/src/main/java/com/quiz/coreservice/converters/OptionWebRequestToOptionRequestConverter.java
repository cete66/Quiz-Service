package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;

@Component("optionWebRequestToOptionRequestConverter")
public class OptionWebRequestToOptionRequestConverter implements Converter<OptionWebRequest, OptionRequest>{

	@Override
	public OptionRequest convert(final OptionWebRequest from) {
		if (from == null) {
			return null;
		}
		return OptionRequest.builder()
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
