package com.quiz.coreservice.repository.entities;

import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.quiz.framework.domain.AbstractModelBean;

@Document
public class Question extends AbstractModelBean {

	@Id
	private String id;
	private String question;
	private List<Option> options;
	private Option answer;

	public Question() {
		
	}
	
	private Question(QuestionBuilder builder) {
		this.id = builder.id;
		this.question = builder.question;
		this.options = builder.options;
		this.answer = builder.answer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Option getAnswer() {
		return answer;
	}

	public void setAnswer(Option answer) {
		this.answer = answer;
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
		Question other = (Question) obj;
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
	
	public QuestionBuilder cloneBuilder() {
		return new QuestionBuilder(this);
	}

	public static QuestionBuilder builder() {
		return new QuestionBuilder();
	}

	public static final class QuestionBuilder {
		private String id;
		private String question;
		private List<Option> options = Collections.emptyList();
		private Option answer;

		private QuestionBuilder() {
		}
		
		public QuestionBuilder(Question question) {
			super();
			this.id = question.id;
			this.question = question.question;
			this.options = question.options;
			this.answer = question.answer;
		}

		public QuestionBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public QuestionBuilder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public QuestionBuilder withOptions(List<Option> options) {
			this.options = options;
			return this;
		}

		public QuestionBuilder withAnswer(Option answer) {
			this.answer = answer;
			return this;
		}

		public Question build() {
			return new Question(this);
		}
	}

}
