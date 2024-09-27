package br.com.gilberto.estudo.biblioteca.fileserver.download.usecase;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.MetadataResource;
import br.com.gilberto.estudo.biblioteca.fileserver.store.service.MetadataService;
import br.com.gilberto.estudo.biblioteca.fileserver.store.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class DownloadUsecase {

	private final StorageService fileStorageService;
	private final MetadataService metadataService;
	
	public MetadataResource execute(final String documentId) {
		var in = this.fileStorageService.findById(documentId);
		return in == null ? null : new MetadataResource(in, metadataService.findById(documentId));  
	}
	
	
}
