package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.repository.GameCRUDRepository;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;

@Service("quizServiceImpl")
@Transactional
public class QuizServiceImpl extends GameCRUDAbstractService<Quiz, QuizRequest, QuizResponse> implements GameCRUDService<QuizRequest, QuizResponse>{
	@Autowired
	public QuizServiceImpl(
			@Qualifier("quizRepository")
			final GameCRUDRepository<Quiz> repository,
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
