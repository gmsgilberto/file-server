package br.com.gilberto.estudo.biblioteca.fileserver.exceptions;

public class FileStorageException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String message, Throwable e) {
		super(message,e);
	}
	
	public FileStorageException(String message) {
		super(message);
	}

}
