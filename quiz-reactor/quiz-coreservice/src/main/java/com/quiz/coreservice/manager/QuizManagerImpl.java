package com.quiz.coreservice.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractManager;
import com.quiz.coreservice.GameCRUDManager;
import com.quiz.coreservice.GameCRUDService;
import com.quiz.coreservice.QuizManager;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListToEntityConverter;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuizWebResponse;

@Service("quizManagerImpl")
public class QuizManagerImpl extends GameCRUDAbstractManager<QuizWebRequest, QuizWebResponse, QuizRequest, QuizResponse> implements QuizManager, GameCRUDManager<QuizWebRequest, QuizWebResponse>{

	private final ListToEntityConverter<QuizWebResponse, AllQuizWebResponse> allQuizConverter;
	
	@Autowired
	public QuizManagerImpl(
			@Qualifier("quizServiceImpl")
			final GameCRUDService<QuizRequest, QuizResponse> service,
			@Qualifier("quizResponseToQuizWebResponseConverter")
			final Converter<QuizResponse, QuizWebResponse> fromCoreConverter,
			@Qualifier("quizWebRequestToQuizRequestConverter")
			final Converter<QuizWebRequest, QuizRequest> toCoreConverter,
			@Qualifier("listQuizWebResponseToAllQuizWebResponseConverter")
			final ListToEntityConverter<QuizWebResponse, AllQuizWebResponse> allQuizConverter) {
		super(service, fromCoreConverter, toCoreConverter);
		this.allQuizConverter = allQuizConverter;
	}

	@Override
	public AllQuizWebResponse generate(List<QuizWebResponse> toConvert) {
		return this.allQuizConverter.convert(toConvert);
	}
	
	@Override
	public QuizWebResponse create(QuizWebRequest entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

}
