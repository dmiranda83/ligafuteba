package br.com.futeba.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2194010777470264955L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable reason) {
		super(message, reason);
	}

}
