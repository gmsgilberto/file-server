package br.com.gilberto.estudo.biblioteca.fileserver.store.model;

import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MetadataResource {

	private final InputStream inputStream;
	private final Metadata fileMetadata;
	
}
