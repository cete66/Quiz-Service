package com.quiz.coreservice.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.framework.converter.Converter;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuizWebRequest;

@Component
public class QuizWebRequestToQuizRequestConverter implements Converter<QuizWebRequest, QuizRequest> {

	private final Converter<QuestionWebRequest, QuestionRequest> qstConv;
	
	@Autowired
	public QuizWebRequestToQuizRequestConverter(
			final Converter<QuestionWebRequest, QuestionRequest> qstConv) {
		this.qstConv = qstConv;
	}
	
	@Override
	public QuizRequest convert(final QuizWebRequest from) {
		if (from == null) {
			return null;
		}
		
		return QuizRequest.builder()
				.withId(from.getId())
				.withName(from.getName())
				.withQuestions(qstListConvert(from.getQuestions()))
				.build();
	}

	protected List<QuestionRequest> qstListConvert(List<QuestionWebRequest> questions) {
		if (questions == null) {
			return null;
		}
		
		return questions.stream()
				.map(e -> qstConv.convert(e))
				.collect(Collectors.toList());
	}

}
