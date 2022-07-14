package br.com.castgroup.cursos.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exceptions.class)
	protected ResponseEntity handlerExceptionExcption(Exceptions exceptions, WebRequest webRequest) {
		
		return handleExceptionInternal(exceptions, exceptions.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
		
		
	}

}
