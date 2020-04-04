package com.quiz.coreservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractService;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;

@Service("quizServiceImpl")
public class QuizServiceImpl extends GameCRUDAbstractService<Quiz, QuizRequest, QuizResponse, String>{
	@Autowired
	public QuizServiceImpl(
			@Qualifier("quizRepository")
			final MongoRepository<Quiz, String> repository,
			@Qualifier("quizToQuizResponseConverter")
			final Converter<Quiz, QuizResponse> fromCoreConverter,
			@Qualifier("quizRequestToQuizConverter")
			final Converter<QuizRequest, Quiz> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}")
			final String errorUpdatingEntity) {
		super(repository, fromCoreConverter, 
				toCoreConverter, errorUpdatingEntity);
	}
}
