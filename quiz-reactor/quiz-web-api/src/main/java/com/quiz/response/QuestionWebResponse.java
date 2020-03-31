package com.quiz.response;

import java.util.Collections;
import java.util.List;

import com.bank.framework.domain.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = QuestionWebResponse.QuestionWebResponseBuilder.class)
@JsonInclude(value = Include.NON_NULL)
public class QuestionWebResponse extends AbstractModelBean {

	private final String id;
	private final String question;
	private final List<OptionWebResponse> options;
	private final OptionWebResponse answer;

	private QuestionWebResponse(QuestionWebResponseBuilder builder) {
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

	public List<OptionWebResponse> getOptions() {
		return options;
	}

	public OptionWebResponse getAnswer() {
		return answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		QuestionWebResponse other = (QuestionWebResponse) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	public QuestionWebResponseBuilder cloneBuilder() {
		return new QuestionWebResponseBuilder(this);
	}

	public static QuestionWebResponseBuilder builder() {
		return new QuestionWebResponseBuilder();
	}

	public static final class QuestionWebResponseBuilder {
		private String id;
		private String question;
		private List<OptionWebResponse> options = Collections.emptyList();
		private OptionWebResponse answer;

		private QuestionWebResponseBuilder() {
		}
		
		public QuestionWebResponseBuilder(QuestionWebResponse request) {
			super();
			this.id = request.id;
			this.question = request.question;
			this.options = request.options;
			this.answer = request.answer;
		}

		public QuestionWebResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuestionWebResponseBuilder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public QuestionWebResponseBuilder withOptions(List<OptionWebResponse> options) {
			this.options = options;
			return this;
		}

		public QuestionWebResponseBuilder withAnswer(OptionWebResponse answer) {
			this.answer = answer;
			return this;
		}

		public QuestionWebResponse build() {
			return new QuestionWebResponse(this);
		}
	}

}
