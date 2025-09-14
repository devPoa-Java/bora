package com.pessoal.bora.api.incoming.errorhandling;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DefaultErrorHandler {
	
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		
		List<ErrorData> messages = ex
				.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fe -> new ErrorData(fe.getDefaultMessage()))
				.collect(Collectors.toList());
		
		
		return new ErrorResponse(messages);
	}

}
