package br.com.gilberto.estudo.biblioteca.fileserver.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.config.FileServerProperties;
import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileStorageException;
import br.com.gilberto.estudo.biblioteca.fileserver.file.local.tools.FileTools;
import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class UploadService {

	private FileServerProperties fileServerProperties;
	private final FileMetadataService metadataService;
	private final FileStorageService fileStorageService;
	
	public FileMetadata execute(final String fileName, final String userid, byte[] content) {
		
		log.info("Salva o arquivo {} no storage ", fileName);
		
		try {

			//Cria os metadados do arquivo
			var metadata = this.metadataService.create(
					FileMetadata
						.builder()
							.fileName(buildFileName(fileName))
							.originalFileName(fileName)
							.userid(userid)
							.size(content.length)
						.build()
					);	

			this.fileStorageService.store(metadata.getDocumentId(), 
										  fileServerProperties.isZip() ?	
											FileTools.zip(fileName, content) : content
										);

			
			metadata.setSucess(true);
			metadata.setCreationTime(new Date());
			return this.metadataService.update(metadata);
			
		}catch (Exception e) {
			throw new FileStorageException("Nao foi possivel gravar o arquivo " + fileName, e);
		}
		
	}

	private String buildFileName(final String fileName) {
		if(this.fileServerProperties.isZip() && !fileName.toLowerCase().endsWith(".zip")) {
			return fileName + ".zip";
		}
		return fileName;
	}
	
}
