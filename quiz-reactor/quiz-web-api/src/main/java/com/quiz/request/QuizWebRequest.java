package com.quiz.request;

import java.util.Collections;
import java.util.List;

import com.bank.framework.domain.AbstractModelBean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = QuizWebRequest.QuizWebRequestBuilder.class)
public class QuizWebRequest extends AbstractModelBean {

	private final String id;
	private final String name;
	private final List<QuestionWebRequest> questions;

	private QuizWebRequest(QuizWebRequestBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.questions = builder.questions;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<QuestionWebRequest> getQuestions() {
		return questions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
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
		QuizWebRequest other = (QuizWebRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}
	
	public QuizWebRequestBuilder cloneBuilder() {
		return new QuizWebRequestBuilder(this);
	}

	public static QuizWebRequestBuilder builder() {
		return new QuizWebRequestBuilder();
	}

	public static final class QuizWebRequestBuilder {
		private String id;
		private String name;
		private List<QuestionWebRequest> questions = Collections.emptyList();

		private QuizWebRequestBuilder() {
		}
		
		public QuizWebRequestBuilder(QuizWebRequest request) {
			super();
			this.id = request.id;
			this.name = request.name;
			this.questions = request.questions;
		}

		public QuizWebRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuizWebRequestBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public QuizWebRequestBuilder withQuestions(List<QuestionWebRequest> questions) {
			this.questions = questions;
			return this;
		}

		public QuizWebRequest build() {
			return new QuizWebRequest(this);
		}
	}

}
