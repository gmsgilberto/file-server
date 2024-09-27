package br.com.gilberto.estudo.biblioteca.fileserver.store.service;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import br.com.gilberto.estudo.biblioteca.fileserver.store.repository.MetadataRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetadataService {
	
	
	private MetadataRepository repository;
	
	/**
	 * Gera um registro de 
	 * @param fileName
	 * @param userid
	 * @param length
	 * @return
	 */
	public Metadata create(Metadata fileMetadata){
		
		return this.repository.create( 
						Metadata
							.builder()
								.documentId(generateDocumentId())
								.creationTime(new Date())
								.fileName(fileMetadata.getFileName())
								.originalFileName(fileMetadata.getOriginalFileName())
								.processid(fileMetadata.getProcessid())
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

	
	public Metadata findById(String documentid) {
		return this.repository.findById(documentid);
	}

	
	public Metadata update(Metadata metadata) throws FileNotFoundException {
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
