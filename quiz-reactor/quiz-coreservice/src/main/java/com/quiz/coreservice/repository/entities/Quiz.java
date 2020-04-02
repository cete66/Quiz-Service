package com.quiz.coreservice.repository.entities;

import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.quiz.framework.domain.AbstractModelBean;

@Document
public class Quiz extends AbstractModelBean {

	@Id
	private String id;
	private String name;
	private List<Question> questions;

	public Quiz() {
		
	}
	
	private Quiz(QuizBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.questions = builder.questions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public static QuizBuilder builder() {
		return new QuizBuilder();
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
		Quiz other = (Quiz) obj;
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

	public QuizBuilder cloneBuilder() {
		return new QuizBuilder(this);
	}

	public static final class QuizBuilder {
		private String id;
		private String name;
		private List<Question> questions = Collections.emptyList();

		private QuizBuilder() {
		}
		
		public QuizBuilder(Quiz quiz) {
			super();
			this.id = quiz.id;
			this.name = quiz.name;
			this.questions = quiz.questions;
		}

		public QuizBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuizBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public QuizBuilder withQuestions(List<Question> questions) {
			this.questions = questions;
			return this;
		}

		public Quiz build() {
			return new Quiz(this);
		}
	}

}
