package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;
import com.quiz.response.OptionWebResponse;

@Component
public class OptionResponseToOptionWebResponseConverter 
implements Converter<OptionResponse, OptionWebResponse> {

	@Override
	public OptionWebResponse convert(OptionResponse from) {
		if (from == null) {
			return null;
		}
		return OptionWebResponse.builder()
				.withCorrect(from.getCorrect())
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
