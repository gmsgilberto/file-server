package br.com.gilberto.estudo.biblioteca.fileserver.repository;

import br.com.gilberto.estudo.biblioteca.fileserver.model.Metadata;

public interface FileMetadataRepository {
	
	/**
	 * Gera um registro de 
	 * @param fileName
	 * @param userid
	 * @param length
	 * @return
	 */
	Metadata create(Metadata fileMetadata);

	
	/**
	 * Atualiza os metadados do arquivo
	 * @param metadata
	 * @return
	 */
	Metadata update(Metadata metadata);


	Metadata findById(String documentid);
	
}
