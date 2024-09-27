package br.com.gilberto.estudo.biblioteca.fileserver.services;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import br.com.gilberto.estudo.biblioteca.fileserver.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileMetadataService {
	
	
	private FileMetadataRepository repository;
	
	/**
	 * Gera um registro de 
	 * @param fileName
	 * @param userid
	 * @param length
	 * @return
	 */
	public FileMetadata create(FileMetadata fileMetadata){
		
		return this.repository.create( 
						FileMetadata
							.builder()
								.documentId(generateDocumentId())
								.creationTime(new Date())
								.fileName(fileMetadata.getFileName())
								.originalFileName(fileMetadata.getOriginalFileName())
								.userid(fileMetadata.getUserid())
								.sucess(fileMetadata.isSucess())
								.size(fileMetadata.getSize())
							.build()
						);
		
	}


	
	/**
	 * Gera um ID único do documento
	 * @return
	 */
	private String generateDocumentId() {
		var documentid = generateId();
		while(findById(documentid) != null) {
			documentid = generateId();
		}
		return documentid;
	}

	
	public FileMetadata findById(String documentid) {
		return this.repository.findById(documentid);
	}

	
	public FileMetadata update(FileMetadata metadata) throws FileNotFoundException {
		var entity = this.repository.findById(metadata.getDocumentId());
		if(entity == null) {
			throw new FileNotFoundException("Nenhum arquivo encontrado com o id " + metadata.getDocumentId());
		}
		entity.setSucess(metadata.isSucess());
		return this.repository.update(entity);
	}
	
	
	
	private synchronized static String generateId() {
		return UUID.randomUUID().toString();
	}
	
	
}
