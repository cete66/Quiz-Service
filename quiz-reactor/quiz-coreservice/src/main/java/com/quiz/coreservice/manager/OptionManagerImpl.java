package com.quiz.coreservice.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quiz.coreservice.GameCRUDAbstractManager;
import com.quiz.coreservice.GameCRUDManager;
import com.quiz.coreservice.GameCRUDService;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

@Service("optionManagerImpl")
public class OptionManagerImpl extends GameCRUDAbstractManager<OptionWebRequest, OptionWebResponse, OptionRequest, OptionResponse> implements GameCRUDManager<OptionWebRequest, OptionWebResponse>{

	@Autowired
	public OptionManagerImpl(
			@Qualifier("optionServiceImpl") 
			final GameCRUDService<OptionRequest, OptionResponse> service,
			@Qualifier("optionResponseToOptionWebResponseConverter")
			final Converter<OptionResponse, OptionWebResponse> fromCoreConverter,
			@Qualifier("optionWebRequestToOptionRequestConverter")
			final Converter<OptionWebRequest, OptionRequest> toCoreConverter) {
		super(service, fromCoreConverter, toCoreConverter);
	}

}
