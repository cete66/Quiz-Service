package com.quiz.coreservice.repository.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.quiz.framework.domain.AbstractModelBean;

@Document
public class Option extends AbstractModelBean {

	@Id
	private String id;
	private String value;
	private Boolean correct;

	private Option(OptionBuilder builder) {
		this.id = builder.id;
		this.value = builder.value;
		this.correct = builder.correct;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Option other = (Option) obj;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public OptionBuilder cloneBuilder() {
		return new OptionBuilder(this);
	}

	public static OptionBuilder builder() {
		return new OptionBuilder();
	}

	public static final class OptionBuilder {
		private String id;
		private String value;
		private Boolean correct;

		private OptionBuilder() {
		}

		public OptionBuilder withId(String id) {
			this.id = id;
			return this;
		}
		
		public OptionBuilder(Option option) {
			super();
			this.id = option.id;
			this.value = option.value;
			this.correct = option.correct;
		}

		public OptionBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public OptionBuilder withCorrect(Boolean correct) {
			this.correct = correct;
			return this;
		}

		public Option build() {
			return new Option(this);
		}
	}
	
	
}
