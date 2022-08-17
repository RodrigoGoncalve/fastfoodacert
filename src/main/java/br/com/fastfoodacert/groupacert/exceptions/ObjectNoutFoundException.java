package br.com.fastfoodacert.groupacert.exceptions;


public class ObjectNoutFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjectNoutFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ObjectNoutFoundException(String message) {
		super(message);
		
	}

}
