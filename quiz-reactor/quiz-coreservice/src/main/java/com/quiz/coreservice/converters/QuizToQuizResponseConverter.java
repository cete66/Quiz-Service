package com.quiz.coreservice.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;

@Component
public class QuizToQuizResponseConverter implements Converter<Quiz, QuizResponse> {

	private final Converter<Question, QuestionResponse> qstConv;
	
	@Autowired
	public QuizToQuizResponseConverter(
			final Converter<Question, QuestionResponse> qstConv) {
		this.qstConv = qstConv;
	}
	
	@Override
	public QuizResponse convert(Quiz from) {
		if (from == null) {
			return null;
		}
		
		return QuizResponse.builder()
				.withId(from.getId())
				.withName(from.getName())
				.withQuestions(qstListConvert(from.getQuestions()))
				.build();
	}

	protected List<QuestionResponse> qstListConvert(List<Question> questions) {
		if (questions == null) {
			return null;
		}
		
		return questions.stream()
				.map(e -> qstConv.convert(e))
				.collect(Collectors.toList());
	}

}
