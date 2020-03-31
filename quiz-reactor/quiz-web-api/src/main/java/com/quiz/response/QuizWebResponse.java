package com.quiz.response;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.quiz.framework.domain.AbstractModelBean;

@JsonDeserialize(builder = QuizWebResponse.QuizWebResponseBuilder.class)
@JsonInclude(value = Include.NON_NULL)
public class QuizWebResponse extends AbstractModelBean {

	private final String id;
	private final String name;
	private final List<QuestionWebResponse> questions;

	private QuizWebResponse(QuizWebResponseBuilder builder) {
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

	public List<QuestionWebResponse> getQuestions() {
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
		QuizWebResponse other = (QuizWebResponse) obj;
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
	
	public QuizWebResponseBuilder cloneBuilder() {
		return new QuizWebResponseBuilder(this);
	}

	public static QuizWebResponseBuilder builder() {
		return new QuizWebResponseBuilder();
	}

	public static final class QuizWebResponseBuilder {
		private String id;
		private String name;
		private List<QuestionWebResponse> questions = Collections.emptyList();

		private QuizWebResponseBuilder() {
		}
		
		public QuizWebResponseBuilder(QuizWebResponse request) {
			super();
			this.id = request.id;
			this.name = request.name;
			this.questions = request.questions;
		}

		public QuizWebResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuizWebResponseBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public QuizWebResponseBuilder withQuestions(List<QuestionWebResponse> questions) {
			this.questions = questions;
			return this;
		}

		public QuizWebResponse build() {
			return new QuizWebResponse(this);
		}
	}

}
