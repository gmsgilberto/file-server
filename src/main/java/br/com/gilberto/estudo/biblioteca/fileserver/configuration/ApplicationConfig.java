package br.com.gilberto.estudo.biblioteca.fileserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "filserserver.config")
public class ApplicationConfig {

	private boolean zip;
	private String storage;
	private String queueService;

}
