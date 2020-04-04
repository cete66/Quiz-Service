package com.quiz.coreservice.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListToEntityConverter;
import com.quiz.response.AllQuizQuestionWebResponse;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

@Component("listQuizWebResponseToAllQuizWebResponseConverter")
public class ListQuizWebResponseToAllQuizWebResponseConverter implements ListToEntityConverter<QuizWebResponse, AllQuizWebResponse>{
	
	private final String qstTagName;
	private final Converter<QuestionWebResponse, AllQuizQuestionWebResponse> questionsConverter;
	
	@Autowired
	public ListQuizWebResponseToAllQuizWebResponseConverter(
			@Value("${com.quiz.coreservice.allQuizWebResponse.questionsTag.name}")
			final String qstTagName,
			@Qualifier("questionWebResponseToAllQuizQuestionWebResponseConverter")
			final Converter<QuestionWebResponse, AllQuizQuestionWebResponse> questionsConverter) {
		this.qstTagName = qstTagName;
		this.questionsConverter = questionsConverter;
	}
	
	@Override
	public AllQuizWebResponse convert(final List<QuizWebResponse> from) {
		
		if (from == null || from.isEmpty()) {
			return null;
		}
		
		Map<String, Map<String, AllQuizQuestionWebResponse>> quizMap = 
				new HashMap<String, Map<String,AllQuizQuestionWebResponse>>();

		from.stream().forEach(quiz -> {
			Map<String,AllQuizQuestionWebResponse> qst = new HashMap<String, AllQuizQuestionWebResponse>();
			IntStream.range(0, quiz.getQuestions().size()).forEach(index -> {
				qst.put(qstTagName+(index+1), questionsConverter.convert(quiz.getQuestions().get(index)));
			});
			quizMap.put(quiz.getName(), qst);
		});
		return AllQuizWebResponse.builder().withQuiz(quizMap).build();
	}

}
