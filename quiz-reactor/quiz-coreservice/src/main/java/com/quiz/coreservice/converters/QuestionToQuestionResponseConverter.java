package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;

@Component("questionToQuestionResponseConverter")
public class QuestionToQuestionResponseConverter 
			implements Converter<Question, QuestionResponse> {

	private final Converter<Option, OptionResponse> optConv;
	
	@Autowired
	public QuestionToQuestionResponseConverter(
			final Converter<Option, OptionResponse> optConv) {
		this.optConv = optConv;
	}

	@Override
	public QuestionResponse convert(final Question from) {
		if (from == null) {
			return null;
		}
		return QuestionResponse.builder()
				.withAnswer(optConv.convert(from.getAnswer()))
				.withId(from.getId())
				.withQuestion(from.getQuestion())
				.withOptions(convertListOptions(from.getOptions()))
				.build();
		
	}

	protected List<OptionResponse> convertListOptions(List<Option> options) {
		if (options == null) {
			return null;
		}
		List<OptionResponse> result = new ArrayList<OptionResponse>();
		options.forEach(e -> result.add(this.optConv.convert(e)));
		
		return result;
	}
}
