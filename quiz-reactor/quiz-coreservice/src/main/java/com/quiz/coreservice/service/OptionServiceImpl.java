package com.quiz.coreservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractService;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@Service("optionServiceImpl")
public class OptionServiceImpl extends 
			GameCRUDAbstractService<Option, OptionRequest, OptionResponse, String>{

	@Autowired
	public OptionServiceImpl(
			@Qualifier("optionRepository")
			final MongoRepository<Option, String> repository,
			@Qualifier("optionToOptionResponseConverter")
			final Converter<Option, OptionResponse> fromCoreConverter,
			@Qualifier("optionRequestToOptionConverter")
			final Converter<OptionRequest, Option> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}") 
			final String errorUpdatingEntity) {
		super(repository, fromCoreConverter, 
				toCoreConverter, errorUpdatingEntity);
	}
}
