package com.quiz.coreservice.converters;

import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.response.OptionWebResponse;

@Component("optionResponseToOptionWebResponseConverter")
public class OptionResponseToOptionWebResponseConverter 
implements Converter<OptionResponse, OptionWebResponse> {

	@Override
	public OptionWebResponse convert(final OptionResponse from) {
		if (from == null) {
			return null;
		}
		return OptionWebResponse.builder()
				.withId(from.getId())
				.withValue(from.getValue())
				.build();
	}

}
