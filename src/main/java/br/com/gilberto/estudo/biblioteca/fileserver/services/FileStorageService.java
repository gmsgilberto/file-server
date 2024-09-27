package br.com.gilberto.estudo.biblioteca.fileserver.services;

import java.io.InputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileStorageException;

public interface FileStorageService {

	void store(String fileId, byte[] content) throws FileStorageException;
	
	
	InputStream findById(String fileId);
	

}
