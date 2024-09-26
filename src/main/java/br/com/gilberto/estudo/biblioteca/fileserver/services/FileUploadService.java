package br.com.gilberto.estudo.biblioteca.fileserver.services;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileStorageException;
import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class FileUploadService {

	private final FileMetadataService metadataService;
	private final FileStorageService fileStorageService;
	
	public FileMetadata execute(final String fileName, final String userid, byte[] content) {
		
		log.info("Salva o arquivo {} no storage ");
		
		try {

			var metadata = this.metadataService.create(
					FileMetadata
						.builder()
							.fileName(fileName)
							.userid(userid)
							.length(content.length)
						.build()
					);	

			this.fileStorageService.store(metadata.getDocumentId(), content);
			metadata.setSucess(true);
			
			log.info(" --> Arquivo {} salvo com sucesso.");
			return this.metadataService.update(metadata);
			
		}catch (Exception e) {
			throw new FileStorageException("Nao foi possivel gravar o arquivo " + fileName, e);
		}
		
	}
	
}
