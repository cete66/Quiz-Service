package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.QuestionWebResponse;

@Service
public class QuestionManagerImpl extends GameCRUDAbstractManager<QuestionWebRequest, QuestionWebResponse, QuestionRequest, QuestionResponse>{

	@Autowired
	public QuestionManagerImpl(final GameCRUDService<QuestionRequest, QuestionResponse> service,
			final Converter<QuestionResponse, QuestionWebResponse> fromCoreConverter,
			final Converter<QuestionWebRequest, QuestionRequest> toCoreConverter,
			final ListConverter<QuestionResponse, QuestionWebResponse> fromCoreListConverter) {
		super(service, fromCoreConverter, toCoreConverter, fromCoreListConverter);
	}

}
