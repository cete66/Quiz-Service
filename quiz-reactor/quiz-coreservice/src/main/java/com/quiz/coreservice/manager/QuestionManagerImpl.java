package com.quiz.coreservice.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractManager;
import com.quiz.coreservice.GameCRUDAbstractService;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.QuestionWebResponse;

@Service("questionManagerImpl")
public class QuestionManagerImpl extends GameCRUDAbstractManager<QuestionWebRequest, QuestionWebResponse, QuestionRequest, QuestionResponse, String, Question>{

	@Autowired
	public QuestionManagerImpl(
			@Qualifier("questionServiceImpl")
			final GameCRUDAbstractService<Question, QuestionRequest, QuestionResponse, String> service,
			@Qualifier("questionResponseToQuestionWebResponseConverter")
			final Converter<QuestionResponse, QuestionWebResponse> fromCoreConverter,
			@Qualifier("questionWebRequestToQuestionRequestConverter")
			final Converter<QuestionWebRequest, QuestionRequest> toCoreConverter) {
		super(service, fromCoreConverter, toCoreConverter);
	}
}
