package com.quiz.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.quiz.framework.domain.AbstractModelBean;

@JsonDeserialize(builder = OptionWebResponse.OptionWebResponseBuilder.class)
@JsonInclude(value = Include.NON_NULL)
public class OptionWebResponse extends AbstractModelBean {

	private final String id;
	private final String value;

	private OptionWebResponse(OptionWebResponseBuilder builder) {
		this.id = builder.id;
		this.value = builder.value;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		OptionWebResponse other = (OptionWebResponse) obj;
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

	public static OptionWebResponseBuilder builder() {
		return new OptionWebResponseBuilder();
	}

	public OptionWebResponseBuilder cloneBuilder() {
		return new OptionWebResponseBuilder(this);
				
	}
	
	public static final class OptionWebResponseBuilder {
		private String id;
		private String value;

		private OptionWebResponseBuilder() {
		}

		public OptionWebResponseBuilder(OptionWebResponse request) {
			super();
			this.id = request.id;
			this.value = request.value;
		}

		public OptionWebResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public OptionWebResponseBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public OptionWebResponse build() {
			return new OptionWebResponse(this);
		}
	}

}
