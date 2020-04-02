package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.QuestionWebResponse;

@Component("questionResponseToQuestionWebResponseConverter")
public class QuestionResponseToQuestionWebResponseConverter 
			implements Converter<QuestionResponse, QuestionWebResponse> {

	private final Converter<OptionResponse, OptionWebResponse> optConv;
	
	@Autowired
	public QuestionResponseToQuestionWebResponseConverter(
			final Converter<OptionResponse, OptionWebResponse> optConv) {
		this.optConv = optConv;
	}

	@Override
	public QuestionWebResponse convert(final QuestionResponse from) {
		if (from == null) {
			return null;
		}
		return QuestionWebResponse.builder()
				.withAnswer(optConv.convert(from.getAnswer()))
				.withId(from.getId())
				.withQuestion(from.getQuestion())
				.withOptions(convertListOptions(from.getOptions()))
				.build();
		
	}

	protected List<OptionWebResponse> convertListOptions(List<OptionResponse> options) {
		if (options == null) {
			return null;
		}
		List<OptionWebResponse> result = new ArrayList<OptionWebResponse>();
		options.forEach(e -> result.add(this.optConv.convert(e)));
		
		return result;
	}
}
