package com.quiz.coreservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.GameCRUDAbstractService;
import com.quiz.coreservice.GameCRUDService;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.GameCRUDRepository;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@Service("optionServiceImpl")
@Transactional
public class OptionServiceImpl extends 
			GameCRUDAbstractService<Option, OptionRequest, OptionResponse> implements GameCRUDService<OptionRequest, OptionResponse>{

	@Autowired
	public OptionServiceImpl(
			@Qualifier("optionRepository")
			final GameCRUDRepository<Option> repository,
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
