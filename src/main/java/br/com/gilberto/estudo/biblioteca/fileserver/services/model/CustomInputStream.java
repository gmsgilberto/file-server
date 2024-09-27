package br.com.gilberto.estudo.biblioteca.fileserver.services.model;

import java.io.InputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.model.Metadata;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomInputStream {

	private final InputStream inputStream;
	private final Metadata fileMetadata;
	
}
