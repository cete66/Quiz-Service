package com.quiz.coreservice.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.quiz.framework.converter.ListToEntityConverter;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;

@Component("listQuizWebResponseToAllQuizWebResponseConverter")
public class ListQuizWebResponseToAllQuizWebResponseConverter implements ListToEntityConverter<QuizWebResponse, AllQuizWebResponse>{
	private final String qstTagName;
	
	@Autowired
	public ListQuizWebResponseToAllQuizWebResponseConverter(
			@Value("${com.quiz.coreservice.allQuizWebResponse.questionsTag.name}")
			final String qstTagName) {
		this.qstTagName = qstTagName;
	}
	
	@Override
	public AllQuizWebResponse convert(final List<QuizWebResponse> from) {
		
		if (from == null || from.isEmpty()) {
			return null;
		}
		
		Map<String, Map<String, QuestionWebResponse>> quizMap = 
				new HashMap<String, Map<String,QuestionWebResponse>>();

		from.stream().forEach(quiz -> {
			Map<String,QuestionWebResponse> qst = new HashMap<String, QuestionWebResponse>();
			IntStream.range(0, quiz.getQuestions().size()).forEach(index -> {
				qst.put(qstTagName+(index+1), quiz.getQuestions().get(index));
			});
			quizMap.put(quiz.getName(), qst);
		});
		return AllQuizWebResponse.builder().withQuiz(quizMap).build();
	}

}
