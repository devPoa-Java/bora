package com.pessoal.bora.api.incoming.errorhandling;

import java.util.Objects;

public class ErrorData {

	private final String message;
	
    public ErrorData(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorData other = (ErrorData) obj;
		return Objects.equals(message, other.message);
	}

	
	
	
}
