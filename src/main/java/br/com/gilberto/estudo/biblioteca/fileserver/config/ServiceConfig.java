package br.com.gilberto.estudo.biblioteca.fileserver.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gilberto.estudo.biblioteca.fileserver.repository.FileMetadataRepository;
import br.com.gilberto.estudo.biblioteca.fileserver.repository.local.FileMetadataRepositoryLocal;
import br.com.gilberto.estudo.biblioteca.fileserver.services.StorageService;
import br.com.gilberto.estudo.biblioteca.fileserver.services.local.FileStorageServiceLocal;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ServiceConfig {
	
	private ApplicationConfig configuration;
	
	@Bean
	FileMetadataRepository fileMetadataRepository() {
		var metadataDirectory = new File(configuration.getLocalStorage(),"metadata");
		if(!metadataDirectory.exists()) {
			metadataDirectory.mkdirs();
		}
		return new FileMetadataRepositoryLocal(metadataDirectory);
	}
	
	@Bean
	StorageService fileStorageService() {
		var storage = new File(configuration.getLocalStorage(),"volume");
		if(!storage.exists()) {
			storage.mkdirs();
		}
		return new FileStorageServiceLocal(storage);
	}
	
}
