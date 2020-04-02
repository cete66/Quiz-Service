package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

@Service
public class OptionManagerIml extends GameCRUDAbstractManager<OptionWebRequest, OptionWebResponse, OptionRequest, OptionResponse>{

	@Autowired
	public OptionManagerIml(final GameCRUDService<OptionRequest, OptionResponse> service,
			final Converter<OptionResponse, OptionWebResponse> fromCoreConverter,
			final Converter<OptionWebRequest, OptionRequest> toCoreConverter,
			final ListConverter<OptionResponse, OptionWebResponse> fromCoreListConverter) {
		super(service, fromCoreConverter, toCoreConverter, fromCoreListConverter);
	}

}
