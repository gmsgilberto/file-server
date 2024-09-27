package br.com.gilberto.estudo.biblioteca.fileserver.usecases;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileStorageException;
import br.com.gilberto.estudo.biblioteca.fileserver.file.local.tools.FileTools;
import br.com.gilberto.estudo.biblioteca.fileserver.model.Metadata;
import br.com.gilberto.estudo.biblioteca.fileserver.queue.service.QueueService;
import br.com.gilberto.estudo.biblioteca.fileserver.services.MetadataService;
import br.com.gilberto.estudo.biblioteca.fileserver.services.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class UploadUseCase {

	private final QueueService queueService;
	private final MetadataService metadataService;
	private final StorageService fileStorageService;
	
	
	public Metadata execute(final String fileName, final String processid, final byte[] content, final boolean zip) {
		
		log.info("Salva o arquivo {} no storage ", fileName);
		
		try {

			//Cria os metadados do arquivo
			var metadata = this.metadataService.create(
					Metadata
						.builder()
							.fileName(buildFileName(fileName, zip))
							.originalFileName(fileName)
							.processid(processid)
							.size(content.length)
						.build()
					);	

			this.fileStorageService.store(metadata.getDocumentId(), 
										  zip ? FileTools.zip(fileName, content) : content
										);


			//Atualiza os metadados do arquivo
			metadata.setSucess(true);
			metadata.setCreationTime(new Date());
			metadata = this.metadataService.update(metadata);

			//Envia a mensagem para a fila de processamento
			this.queueService.notify(metadata);
			
			return metadata;
			
		}catch (Exception e) {
			throw new FileStorageException("Nao foi possivel gravar o arquivo " + fileName, e);
		}
		
	}

	private String buildFileName(final String fileName, boolean zip) {
		if(zip && !fileName.toLowerCase().endsWith(".zip")) {
			return fileName + ".zip";
		}
		return fileName;
	}
	
}
