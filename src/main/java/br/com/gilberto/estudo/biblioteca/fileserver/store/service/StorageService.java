package br.com.gilberto.estudo.biblioteca.fileserver.store.service;

import java.io.InputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileServerException;

public interface StorageService {

	void store(String fileId, byte[] content) throws FileServerException;
	
	
	InputStream findById(String fileId);
	

}
