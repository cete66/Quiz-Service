package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.repository.QuizRepository;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

@Service
@Transactional
public class QuizServiceImpl extends 
			GameCRUDAbstractService<Quiz, QuizRequest, QuizResponse>{

	@Autowired
	public QuizServiceImpl(final QuizRepository repository, 
			final Converter<Quiz, QuizResponse> fromCoreConverter,
			final ListConverter<Quiz, QuizResponse> listConverter, 
			final Converter<QuizRequest, Quiz> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}")
			final String errorUpdatingEntity) {
		super(repository, fromCoreConverter, listConverter, 
				toCoreConverter, errorUpdatingEntity);
	}
}
