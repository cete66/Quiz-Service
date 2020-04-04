package com.quiz.response;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = AllQuizQuestionWebResponse.AllQuizQuestionWebResponseBuilder.class)
@JsonInclude(value = Include.NON_NULL)
public class AllQuizQuestionWebResponse {

	private final String id;
	private final String question;
	private final List<String> options;
	private final String answer;

	private AllQuizQuestionWebResponse(AllQuizQuestionWebResponseBuilder builder) {
		this.id = builder.id;
		this.question = builder.question;
		this.options = builder.options;
		this.answer = builder.answer;
	}

	public String getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getOptions() {
		return options;
	}

	public String getAnswer() {
		return answer;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(answer, id, options, question);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllQuizQuestionWebResponse other = (AllQuizQuestionWebResponse) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(id, other.id)
				&& Objects.equals(options, other.options) && Objects.equals(question, other.question);
	}

	public AllQuizQuestionWebResponseBuilder cloneBuilder() {
		return new AllQuizQuestionWebResponseBuilder(this);
	}

	public static AllQuizQuestionWebResponseBuilder builder() {
		return new AllQuizQuestionWebResponseBuilder();
	}

	public static final class AllQuizQuestionWebResponseBuilder {
		private String id;
		private String question;
		private List<String> options = Collections.emptyList();
		private String answer;

		private AllQuizQuestionWebResponseBuilder() {
		}
		
		public AllQuizQuestionWebResponseBuilder(AllQuizQuestionWebResponse request) {
			super();
			this.id = request.id;
			this.question = request.question;
			this.options = request.options;
			this.answer = request.answer;
		}

		public AllQuizQuestionWebResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public AllQuizQuestionWebResponseBuilder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public AllQuizQuestionWebResponseBuilder withOptions(List<String> options) {
			this.options = options;
			return this;
		}

		public AllQuizQuestionWebResponseBuilder withAnswer(String answer) {
			this.answer = answer;
			return this;
		}

		public AllQuizQuestionWebResponse build() {
			return new AllQuizQuestionWebResponse(this);
		}
	}
}
