package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;

@Component
public class QuestionWebRequestToQuestionRequestConverter 
			implements Converter<QuestionWebRequest, QuestionRequest> {

	private final Converter<OptionWebRequest, OptionRequest> optConv;
	
	@Autowired
	public QuestionWebRequestToQuestionRequestConverter(
			final Converter<OptionWebRequest, OptionRequest> optConv) {
		this.optConv = optConv;
	}

	@Override
	public QuestionRequest convert(QuestionWebRequest from) {
		if (from == null) {
			return null;
		}
		return QuestionRequest.builder()
				.withAnswer(optConv.convert(from.getAnswer()))
				.withId(from.getId())
				.withQuestion(from.getQuestion())
				.withOptions(convertListOptions(from.getOptions()))
				.build();
		
	}

	protected List<OptionRequest> convertListOptions(List<OptionWebRequest> options) {
		if (options == null) {
			return null;
		}
		List<OptionRequest> result = new ArrayList<OptionRequest>();
		options.forEach(e -> result.add(this.optConv.convert(e)));
		
		return result;
	}
}
