package com.pessoal.bora.api.incoming.errorhandling;

import java.util.List;
import java.util.Objects;

public class ErrorResponse {
	
	List<ErrorData> errors;

	public ErrorResponse(List<ErrorData> errors) {
		super();
		this.errors = errors;
	}

	public List<ErrorData> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorData> errors) {
		this.errors = errors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		return Objects.equals(errors, other.errors);
	}
	
	

}
