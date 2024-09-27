package br.com.gilberto.estudo.biblioteca.fileserver.configuration;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gilberto.estudo.biblioteca.fileserver.store.service.StorageService;
import br.com.gilberto.estudo.biblioteca.fileserver.store.service.local.FileStorageServiceLocal;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class StorageServiceConfig {
	
	private ApplicationConfig configuration;
	
	
	@Bean
	StorageService fileStorageService() {
		
		if("local".equalsIgnoreCase(configuration.getStorage())) {
			var storage = new File(configuration.getStorage(),"volume");
			if(!storage.exists()) {
				storage.mkdirs();
			}
			return new FileStorageServiceLocal(storage);
		}
		
		throw new IllegalArgumentException("Storage repository nao implementado: " + configuration.getStorage());
	}
	
}
