package com.quiz.coreservice.domain;

import com.quiz.framework.domain.AbstractModelBean;

public class OptionResponse extends AbstractModelBean {

	private final String id;
	private final String value;

	private OptionResponse(OptionResponseBuilder builder) {
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
		OptionResponse other = (OptionResponse) obj;
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
	
	public OptionResponseBuilder cloneBuilder() {
		return new OptionResponseBuilder(this);
	}

	public static OptionResponseBuilder builder() {
		return new OptionResponseBuilder();
	}

	public static final class OptionResponseBuilder {
		private String id;
		private String value;

		private OptionResponseBuilder() {
		}
		
	
		public OptionResponseBuilder(OptionResponse request) {
			super();
			this.id = request.id;
			this.value = request.value;
		}

		public OptionResponseBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public OptionResponseBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public OptionResponse build() {
			return new OptionResponse(this);
		}
	}
}
