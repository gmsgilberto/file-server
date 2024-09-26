package br.com.gilberto.estudo.biblioteca.fileserver.repository;

import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;

public interface FileMetadataRepository {
	
	/**
	 * Gera um registro de 
	 * @param fileName
	 * @param userid
	 * @param length
	 * @return
	 */
	FileMetadata create(FileMetadata fileMetadata);

	
	/**
	 * Atualiza os metadados do arquivo
	 * @param metadata
	 * @return
	 */
	FileMetadata update(FileMetadata metadata);


	FileMetadata findById(String documentid);
	
}
