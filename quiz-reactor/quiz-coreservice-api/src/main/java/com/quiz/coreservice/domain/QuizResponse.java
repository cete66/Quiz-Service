package com.quiz.coreservice.domain;

import java.util.Collections;
import java.util.List;

import com.bank.framework.domain.AbstractModelBean;

public class QuizResponse extends AbstractModelBean {

	private final String id;
	private final String name;
	private final List<QuestionResponse> questions;

	private QuizResponse(QuizResponseBuilder builder) {
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

	public List<QuestionResponse> getQuestions() {
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
		QuizResponse other = (QuizResponse) obj;
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
	
	public QuizResponseBuilder cloneBuilder() {
		return new QuizResponseBuilder(this);
	}

	public static QuizResponseBuilder builder() {
		return new QuizResponseBuilder();
	}

	public static final class QuizResponseBuilder {
		private String id;
		private String name;
		private List<QuestionResponse> questions = Collections.emptyList();

		private QuizResponseBuilder() {
		}
		
		public QuizResponseBuilder(QuizResponse request) {
			super();
			this.id = request.id;
			this.name = request.name;
			this.questions = request.questions;
		}

		public QuizResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuizResponseBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public QuizResponseBuilder withQuestions(List<QuestionResponse> questions) {
			this.questions = questions;
			return this;
		}

		public QuizResponse build() {
			return new QuizResponse(this);
		}
	}
}
