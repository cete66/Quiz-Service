package com.quiz.coreservice.domain;

import java.util.Collections;
import java.util.List;

import com.quiz.framework.domain.AbstractModelBean;

public class QuizRequest extends AbstractModelBean {

	private final String id;
	private final String name;
	private final List<QuestionRequest> questions;

	private QuizRequest(QuizRequestBuilder builder) {
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

	public List<QuestionRequest> getQuestions() {
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
		QuizRequest other = (QuizRequest) obj;
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
	
	public QuizRequestBuilder cloneBuilder() {
		return new QuizRequestBuilder(this);
	}

	public static QuizRequestBuilder builder() {
		return new QuizRequestBuilder();
	}

	public static final class QuizRequestBuilder {
		private String id;
		private String name;
		private List<QuestionRequest> questions = Collections.emptyList();

		private QuizRequestBuilder() {
		}
		
		public QuizRequestBuilder(QuizRequest request) {
			super();
			this.id = request.id;
			this.name = request.name;
			this.questions = request.questions;
		}

		public QuizRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuizRequestBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public QuizRequestBuilder withQuestions(List<QuestionRequest> questions) {
			this.questions = questions;
			return this;
		}

		public QuizRequest build() {
			return new QuizRequest(this);
		}
	}
}
