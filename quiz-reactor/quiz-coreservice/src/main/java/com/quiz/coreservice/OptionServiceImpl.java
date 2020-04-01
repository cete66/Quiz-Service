package com.quiz.coreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.OptionRepository;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

@Service
@Transactional
public class OptionServiceImpl extends 
			GameCRUDAbstractService<Option, OptionRequest, OptionResponse> {

	@Autowired
	public OptionServiceImpl(OptionRepository repository, 
			Converter<Option, OptionResponse> fromCoreConverter,
			ListConverter<Option, OptionResponse> listConverter, 
			Converter<OptionRequest, Option> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}") 
			String errorUpdatingEntity) {
		super(repository, fromCoreConverter, listConverter, 
				toCoreConverter, errorUpdatingEntity);
	}
}
