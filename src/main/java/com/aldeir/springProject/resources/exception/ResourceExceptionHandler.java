package com.aldeir.springProject.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.aldeir.springProject.services.ObjectNotFoundException;

@ControllerAdvice 
public class ResourceExceptionHandler {
	//manipulador de excecao do  recurso
  

	@ExceptionHandler(ObjectNotFoundException.class) //indica que esse methodo trata excecao desse tipo  ObjectNotFoundException.class
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	}

