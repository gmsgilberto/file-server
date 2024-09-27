package br.com.gilberto.estudo.biblioteca.fileserver.services;

import org.springframework.stereotype.Service;

import br.com.gilberto.estudo.biblioteca.fileserver.services.model.CustomInputStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class DownloadService {

	private final StorageService fileStorageService;
	private final MetadataService metadataService;
	
	public CustomInputStream execute(final String documentId) {
		
		var in = this.fileStorageService.findById(documentId);
		CustomInputStream resource = null;
		
		if(in != null) {
			var metadata = metadataService.findById(documentId);
			resource = new CustomInputStream(in, metadata) ; 
		}
		
		return resource;
		
	}
	
	
}
