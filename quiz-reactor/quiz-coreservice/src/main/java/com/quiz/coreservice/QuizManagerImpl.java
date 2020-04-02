package com.quiz.coreservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;
import com.quiz.framework.converter.ListToEntityConverter;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuizWebResponse;

@Service
public class QuizManagerImpl extends GameCRUDAbstractManager<QuizWebRequest, QuizWebResponse, QuizRequest, QuizResponse> implements QuizManager{

	private final ListToEntityConverter<QuizWebResponse, AllQuizWebResponse> allQuizConverter;
	
	@Autowired
	public QuizManagerImpl(final GameCRUDService<QuizRequest, QuizResponse> service,
			final Converter<QuizResponse, QuizWebResponse> fromCoreConverter,
			final Converter<QuizWebRequest, QuizRequest> toCoreConverter,
			final ListConverter<QuizResponse, QuizWebResponse> fromCoreListConverter,
			final ListToEntityConverter<QuizWebResponse, AllQuizWebResponse> allQuizConverter) {
		super(service, fromCoreConverter, toCoreConverter, fromCoreListConverter);
		this.allQuizConverter = allQuizConverter;
	}

	@Override
	public AllQuizWebResponse generate(List<QuizWebResponse> toConvert) {
		return this.allQuizConverter.convert(toConvert);
	}

}
