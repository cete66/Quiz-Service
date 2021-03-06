package com.quiz.response;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.quiz.framework.domain.AbstractModelBean;

@JsonDeserialize(builder = AllQuizWebResponse.AllQuizWebResponseBuilder.class)
public class AllQuizWebResponse extends AbstractModelBean{

	private final Map<String, Map<String, AllQuizQuestionWebResponse>> quiz;


	private AllQuizWebResponse(AllQuizWebResponseBuilder builder) {
		this.quiz = builder.quiz;
	}

	
	
	public Map<String, Map<String, AllQuizQuestionWebResponse>> getQuiz() {
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
	
	public AllQuizWebResponseBuilder cloneBuilder() {
		return new AllQuizWebResponseBuilder(this);
	}

	public static AllQuizWebResponseBuilder builder() {
		return new AllQuizWebResponseBuilder();
	}

	public static final class AllQuizWebResponseBuilder {
		private Map<String, Map<String, AllQuizQuestionWebResponse>> quiz = Collections.emptyMap();

		private AllQuizWebResponseBuilder() {
		}
		
		public AllQuizWebResponseBuilder(AllQuizWebResponse allQuiz) {
			super();
			this.quiz = allQuiz.quiz;
		}

		public AllQuizWebResponseBuilder withQuiz(Map<String, Map<String, AllQuizQuestionWebResponse>> quiz) {
			this.quiz = quiz;
			return this;
		}

		public AllQuizWebResponse build() {
			return new AllQuizWebResponse(this);
		}
	}
	
	
	
}
