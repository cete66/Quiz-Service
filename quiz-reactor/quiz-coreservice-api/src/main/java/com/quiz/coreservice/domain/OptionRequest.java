package com.quiz.coreservice.domain;

import com.quiz.framework.domain.AbstractModelBean;

public class OptionRequest extends AbstractModelBean {

	private final String id;
	private final String value;

	private OptionRequest(OptionRequestBuilder builder) {
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
		OptionRequest other = (OptionRequest) obj;
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
	
	public OptionRequestBuilder cloneBuilder() {
		return new OptionRequestBuilder(this);
	}

	public static OptionRequestBuilder builder() {
		return new OptionRequestBuilder();
	}

	public static final class OptionRequestBuilder {
		private String id;
		private String value;

		private OptionRequestBuilder() {
		}
		
	
		public OptionRequestBuilder(OptionRequest request) {
			super();
			this.id = request.id;
			this.value = request.value;
		}

		public OptionRequestBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public OptionRequestBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public OptionRequest build() {
			return new OptionRequest(this);
		}
	}

}
