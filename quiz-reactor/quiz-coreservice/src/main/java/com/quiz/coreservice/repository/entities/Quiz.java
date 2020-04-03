package com.quiz.coreservice.repository.entities;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
		return Objects.hash(id, name, questions);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(questions, other.questions);
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
