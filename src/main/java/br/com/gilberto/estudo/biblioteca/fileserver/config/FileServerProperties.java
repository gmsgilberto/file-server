package br.com.gilberto.estudo.biblioteca.fileserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "filserserver.config")
public class FileServerProperties {

	private boolean zip;

}
