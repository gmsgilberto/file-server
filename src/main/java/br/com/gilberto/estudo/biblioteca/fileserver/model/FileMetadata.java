package br.com.gilberto.estudo.biblioteca.fileserver.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileMetadata implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String documentId;
	private final String fileName;
	private final String userid;
	private final int length;
	private final Date creationTime;
	private boolean sucess;

}
