package com.quiz.request;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.quiz.framework.domain.AbstractModelBean;

@JsonDeserialize(builder = QuestionWebRequest.QuestionWebRequestBuilder.class)
public class QuestionWebRequest extends AbstractModelBean {

	private final String id;
	@NotBlank
	private final String question;
	@NotEmpty
	private final List<OptionWebRequest> options;
	@NotNull
	private final OptionWebRequest answer;

	private QuestionWebRequest(QuestionWebRequestBuilder builder) {
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

	public List<OptionWebRequest> getOptions() {
		return options;
	}

	public OptionWebRequest getAnswer() {
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
		QuestionWebRequest other = (QuestionWebRequest) obj;
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
	
	public QuestionWebRequestBuilder cloneBuilder() {
		return new QuestionWebRequestBuilder(this);
	}

	public static QuestionWebRequestBuilder builder() {
		return new QuestionWebRequestBuilder();
	}

	public static final class QuestionWebRequestBuilder {
		private String id;
		private String question;
		private List<OptionWebRequest> options = Collections.emptyList();
		private OptionWebRequest answer;

		private QuestionWebRequestBuilder() {
		}
		
		public QuestionWebRequestBuilder(QuestionWebRequest request) {
			super();
			this.id = request.id;
			this.question = request.question;
			this.options = request.options;
			this.answer = request.answer;
		}

		public QuestionWebRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuestionWebRequestBuilder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public QuestionWebRequestBuilder withOptions(List<OptionWebRequest> options) {
			this.options = options;
			return this;
		}

		public QuestionWebRequestBuilder withAnswer(OptionWebRequest answer) {
			this.answer = answer;
			return this;
		}

		public QuestionWebRequest build() {
			return new QuestionWebRequest(this);
		}
	}

}
