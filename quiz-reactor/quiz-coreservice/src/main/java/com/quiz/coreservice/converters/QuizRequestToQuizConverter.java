package com.quiz.coreservice.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.framework.converter.Converter;

@Component
public class QuizRequestToQuizConverter implements Converter<QuizRequest, Quiz> {

	private final Converter<QuestionRequest, Question> qstConv;
	
	@Autowired
	public QuizRequestToQuizConverter(
			final Converter<QuestionRequest, Question> qstConv) {
		this.qstConv = qstConv;
	}
	
	@Override
	public Quiz convert(final QuizRequest from) {
		if (from == null) {
			return null;
		}
		
		return Quiz.builder()
				.withId(from.getId())
				.withName(from.getName())
				.withQuestions(qstListConvert(from.getQuestions()))
				.build();
	}

	protected List<Question> qstListConvert(List<QuestionRequest> questions) {
		if (questions == null) {
			return null;
		}
		
		return questions.stream()
				.map(e -> qstConv.convert(e))
				.collect(Collectors.toList());
	}

}
