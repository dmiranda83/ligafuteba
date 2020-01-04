package br.com.futeba.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.futeba.models.ErrorDetails;
import br.com.futeba.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourcesExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlerResourceNotFound(
			ResourceNotFoundException e, HttpServletRequest request) {

		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(404L);
		erro.setTitle(e.getMessage());
		erro.setMessage("Check if the URI/ID or parameters are correct!");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
