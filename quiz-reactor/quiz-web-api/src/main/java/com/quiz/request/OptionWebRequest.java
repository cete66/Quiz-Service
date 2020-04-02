package com.quiz.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.quiz.framework.domain.AbstractModelBean;

@JsonDeserialize(builder = OptionWebRequest.OptionWebRequestBuilder.class)
public class OptionWebRequest extends AbstractModelBean {

	private final String id;
	@NotBlank
	private final String value;
	@NotNull
	private final Boolean correct;

	private OptionWebRequest(OptionWebRequestBuilder builder) {
		this.id = builder.id;
		this.value = builder.value;
		this.correct = builder.correct;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public Boolean getCorrect() {
		return correct;
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
		OptionWebRequest other = (OptionWebRequest) obj;
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

	public static OptionWebRequestBuilder builder() {
		return new OptionWebRequestBuilder();
	}

	public OptionWebRequestBuilder cloneBuilder() {
		return new OptionWebRequestBuilder(this);
				
	}
	
	public static final class OptionWebRequestBuilder {
		private String id;
		private String value;
		private Boolean correct;

		private OptionWebRequestBuilder() {
		}

		public OptionWebRequestBuilder(OptionWebRequest request) {
			super();
			this.id = request.id;
			this.value = request.value;
			this.correct = request.correct;
		}

		public OptionWebRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public OptionWebRequestBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public OptionWebRequestBuilder withCorrect(Boolean correct) {
			this.correct = correct;
			return this;
		}

		public OptionWebRequest build() {
			return new OptionWebRequest(this);
		}
	}

}
