package br.com.gilberto.estudo.biblioteca.fileserver.services.model;

import java.io.InputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomInputStream {

	private final InputStream inputStream;
	private final FileMetadata fileMetadata;
	
}
