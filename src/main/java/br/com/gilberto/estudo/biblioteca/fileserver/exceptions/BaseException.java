package br.com.gilberto.estudo.biblioteca.fileserver.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException(String message) {
		super(message);
		log.error(message, this);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

}
