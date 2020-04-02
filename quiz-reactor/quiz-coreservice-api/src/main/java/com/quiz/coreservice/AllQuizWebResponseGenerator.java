package com.quiz.coreservice;

import java.util.List;

import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuizWebResponse;

public interface AllQuizWebResponseGenerator {

	AllQuizWebResponse generate(final List<QuizWebResponse> toConvert);
}
