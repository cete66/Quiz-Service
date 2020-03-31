package com.quiz.response;

import java.util.Map;

import com.bank.framework.domain.AbstractModelBean;
import javax.annotation.Generated;
import java.util.Collections;

public class AllQuizWebResponse extends AbstractModelBean{

	private final Map<String, Map<String, QuestionWebResponse>> quiz;



	@Generated("SparkTools")
	private AllQuizWebResponse(AllQuizWebResponseBuilder builder) {
		this.quiz = builder.quiz;
	}

	
	
	public Map<String, Map<String, QuestionWebResponse>> getQuiz() {
		return quiz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllQuizWebResponse other = (AllQuizWebResponse) obj;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

	public static AllQuizWebResponseBuilder builder() {
		return new AllQuizWebResponseBuilder();
	}

	public static final class AllQuizWebResponseBuilder {
		private Map<String, Map<String, QuestionWebResponse>> quiz = Collections.emptyMap();

		private AllQuizWebResponseBuilder() {
		}

		public AllQuizWebResponseBuilder withQuiz(Map<String, Map<String, QuestionWebResponse>> quiz) {
			this.quiz = quiz;
			return this;
		}

		public AllQuizWebResponse build() {
			return new AllQuizWebResponse(this);
		}
	}
	
	
	
}
