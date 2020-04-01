package com.quiz.coreservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.QuestionWebRequest;

@Component
public class QuestionRequestToQuestionConverter 
			implements Converter<QuestionRequest, Question> {

	private final Converter<OptionRequest, Option> optConv;
	
	@Autowired
	public QuestionRequestToQuestionConverter(
			final Converter<OptionRequest, Option> optConv) {
		this.optConv = optConv;
	}

	@Override
	public Question convert(QuestionRequest from) {
		if (from == null) {
			return null;
		}
		return Question.builder()
				.withAnswer(optConv.convert(from.getAnswer()))
				.withId(from.getId())
				.withQuestion(from.getQuestion())
				.withOptions(convertListOptions(from.getOptions()))
				.build();
		
	}

	protected List<Option> convertListOptions(List<OptionRequest> options) {
		if (options == null) {
			return null;
		}
		List<Option> result = new ArrayList<Option>();
		options.forEach(e -> result.add(this.optConv.convert(e)));
		
		return result;
	}
}
