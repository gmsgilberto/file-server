package br.com.gilberto.estudo.biblioteca.fileserver.exceptions;

public class FileServerException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public FileServerException(String message, Throwable e) {
		super(message,e);
	}
	
	public FileServerException(String message) {
		super(message);
	}

}
