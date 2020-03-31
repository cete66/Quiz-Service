package com.quiz.coreservice.domain;

import java.util.Collections;
import java.util.List;

import com.quiz.framework.domain.AbstractModelBean;

public class QuestionRequest extends AbstractModelBean{

	private final String id;
	private final String question;
	private final List<OptionRequest> options;
	private final OptionRequest answer;

	private QuestionRequest(QuestionRequestBuilder builder) {
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

	public List<OptionRequest> getOptions() {
		return options;
	}

	public OptionRequest getAnswer() {
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
		QuestionRequest other = (QuestionRequest) obj;
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
	
	public QuestionRequestBuilder cloneBuilder() {
		return new QuestionRequestBuilder(this);
	}

	public static QuestionRequestBuilder builder() {
		return new QuestionRequestBuilder();
	}

	public static final class QuestionRequestBuilder {
		private String id;
		private String question;
		private List<OptionRequest> options = Collections.emptyList();
		private OptionRequest answer;

		private QuestionRequestBuilder() {
		}
		
		public QuestionRequestBuilder(QuestionRequest request) {
			super();
			this.id = request.id;
			this.question = request.question;
			this.options = request.options;
			this.answer = request.answer;
		}

		public QuestionRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuestionRequestBuilder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public QuestionRequestBuilder withOptions(List<OptionRequest> options) {
			this.options = options;
			return this;
		}

		public QuestionRequestBuilder withAnswer(OptionRequest answer) {
			this.answer = answer;
			return this;
		}

		public QuestionRequest build() {
			return new QuestionRequest(this);
		}
	}
}
