package com.quiz.coreservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractService;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;

@Service("questionServiceImpl")
public class QuestionServiceImpl extends GameCRUDAbstractService<Question, QuestionRequest, QuestionResponse, String>{

	@Autowired
	public QuestionServiceImpl(
			@Qualifier("questionRepository")
			final MongoRepository<Question, String> repository,
			@Qualifier("questionToQuestionResponseConverter")
			final Converter<Question, QuestionResponse> fromCoreConverter,
			@Qualifier("questionRequestToQuestionConverter")
			final Converter<QuestionRequest, Question> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}")
			final String errorUpdatingEntity) {
		super(repository, fromCoreConverter, 
				toCoreConverter, errorUpdatingEntity);
	}

}
