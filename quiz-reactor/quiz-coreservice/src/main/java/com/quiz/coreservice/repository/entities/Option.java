package com.quiz.coreservice.repository.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.quiz.framework.domain.AbstractModelBean;

@Document
public class Option extends AbstractModelBean {

	@Id
	private String id;
	private String value;

	public Option() {
		
	}
	
	private Option(OptionBuilder builder) {
		this.id = builder.id;
		this.value = builder.value;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, value);
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
		return Objects.equals(id, other.id) && Objects.equals(value, other.value);
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
		}

		public OptionBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public Option build() {
			return new Option(this);
		}
	}
	
	
}
