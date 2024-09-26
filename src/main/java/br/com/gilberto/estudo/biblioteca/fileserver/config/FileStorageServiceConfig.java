package br.com.gilberto.estudo.biblioteca.fileserver.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gilberto.estudo.biblioteca.fileserver.repository.FileMetadataRepository;
import br.com.gilberto.estudo.biblioteca.fileserver.repository.local.FileMetadataRepositoryLocal;
import br.com.gilberto.estudo.biblioteca.fileserver.services.FileStorageService;
import br.com.gilberto.estudo.biblioteca.fileserver.services.local.FileStorageServiceLocal;

@Configuration
public class FileStorageServiceConfig {
	
	@Bean
	FileMetadataRepository fileMetadataRepository() {
		var metadataDirectory = new File("target/storage/metadata/");
		if(!metadataDirectory.exists()) {
			metadataDirectory.mkdirs();
		}
		return new FileMetadataRepositoryLocal(metadataDirectory);
	}
	
	@Bean
	FileStorageService fileStorageService() {
		var storage = new File("target/storage/dest/");
		if(!storage.exists()) {
			storage.mkdirs();
		}
		return new FileStorageServiceLocal(storage);
	}
	
}
