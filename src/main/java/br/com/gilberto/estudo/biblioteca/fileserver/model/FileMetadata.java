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

	private String documentId;
	private String fileName;
	private String originalFileName;
	private String userid;
	private int size;
	private Date creationTime;
	private boolean sucess;

}
