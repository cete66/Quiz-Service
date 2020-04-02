package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.GameCRUDRepository;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;

@Service("questionServiceImpl")
@Transactional
public class QuestionServiceImpl extends GameCRUDAbstractService<Question, QuestionRequest, QuestionResponse> implements GameCRUDService<QuestionRequest, QuestionResponse>{

	@Autowired
	public QuestionServiceImpl(
			@Qualifier("questionRepository")
			final GameCRUDRepository<Question> repository,
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
