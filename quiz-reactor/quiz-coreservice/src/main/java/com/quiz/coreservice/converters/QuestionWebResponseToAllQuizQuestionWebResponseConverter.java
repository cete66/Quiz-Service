package com.quiz.coreservice.converters;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.quiz.framework.converter.Converter;
import com.quiz.response.AllQuizQuestionWebResponse;
import com.quiz.response.QuestionWebResponse;

@Component("questionWebResponseToAllQuizQuestionWebResponseConverter")
public class QuestionWebResponseToAllQuizQuestionWebResponseConverter implements Converter<QuestionWebResponse, AllQuizQuestionWebResponse>{

	@Override
	public AllQuizQuestionWebResponse convert(QuestionWebResponse src) {
		if (src == null) {
			return null;
		}

		List<String> allOptions = Optional.ofNullable(src.getOptions()).orElseGet(() -> Collections.emptyList())
								.stream().map(o -> o.getValue()).collect(Collectors.toList());
		
		return AllQuizQuestionWebResponse.builder()
									.withId(src.getId())
									.withAnswer(src.getAnswer().getValue())
									.withOptions(allOptions)
									.withQuestion(src.getQuestion()).build();
	}

}
