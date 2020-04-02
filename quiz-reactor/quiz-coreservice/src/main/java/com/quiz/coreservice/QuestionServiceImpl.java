package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.GameCRUDRepository;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

@Service
@Transactional
public class QuestionServiceImpl extends 
			GameCRUDAbstractService<Question, QuestionRequest, QuestionResponse>{

	@Autowired
	public QuestionServiceImpl(final GameCRUDRepository<Question> repository,
			final Converter<Question, QuestionResponse> fromCoreConverter,
			final ListConverter<Question, QuestionResponse> listConverter,
			final Converter<QuestionRequest, Question> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}")
			final String errorUpdatingEntity) {
		super(repository, fromCoreConverter, listConverter, 
				toCoreConverter, errorUpdatingEntity);
	}

}
