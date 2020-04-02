package com.quiz.coreservice.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.framework.converter.Converter;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

@Component("quizResponseToQuizWebResponseConverter")
public class QuizResponseToQuizWebResponseConverter 
implements Converter<QuizResponse, QuizWebResponse> {

	private final Converter<QuestionResponse, QuestionWebResponse> qstConv;
	
	@Autowired
	public QuizResponseToQuizWebResponseConverter(
			final Converter<QuestionResponse, QuestionWebResponse> qstConv) {
		this.qstConv = qstConv;
	}
	
	@Override
	public QuizWebResponse convert(final QuizResponse from) {
		if (from == null) {
			return null;
		}
		
		return QuizWebResponse.builder()
				.withId(from.getId())
				.withName(from.getName())
				.withQuestions(qstListConvert(from.getQuestions()))
				.build();
	}

	protected List<QuestionWebResponse> qstListConvert(List<QuestionResponse> questions) {
		if (questions == null) {
			return null;
		}
		
		return questions.stream()
				.map(e -> qstConv.convert(e))
				.collect(Collectors.toList());
	}

}
