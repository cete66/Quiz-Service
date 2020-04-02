package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.QuestionWebResponse;

@Service("questionManagerImpl")
public class QuestionManagerImpl extends GameCRUDAbstractManager<QuestionWebRequest, QuestionWebResponse, QuestionRequest, QuestionResponse> implements GameCRUDManager<QuestionWebRequest, QuestionWebResponse>{

	@Autowired
	public QuestionManagerImpl(
			@Qualifier("questionServiceImpl")
			final GameCRUDService<QuestionRequest, QuestionResponse> service,
			@Qualifier("questionResponseToQuestionWebResponseConverter")
			final Converter<QuestionResponse, QuestionWebResponse> fromCoreConverter,
			@Qualifier("questionWebRequestToQuestionRequestConverter")
			final Converter<QuestionWebRequest, QuestionRequest> toCoreConverter) {
		super(service, fromCoreConverter, toCoreConverter);
	}

}
