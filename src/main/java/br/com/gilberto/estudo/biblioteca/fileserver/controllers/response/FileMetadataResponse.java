package br.com.gilberto.estudo.biblioteca.fileserver.controllers.response;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import lombok.Getter;

@Getter
public class FileMetadataResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String documentId;
	private final String fileName;
	private final String originalfileName;
	private final String userid;
	private final String size;
	private final String creationTime;
	private boolean sucess;

	public FileMetadataResponse(FileMetadata entity) {
		this.documentId = entity.getDocumentId();
		this.sucess = entity.isSucess();
		this.fileName = entity.getFileName();
		this.originalfileName = entity.getOriginalFileName(); 
		this.userid = entity.getUserid();
		this.size = entity.getSize() +  " bytes"; 
		this.creationTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSS").format(entity.getCreationTime());
	}
	

}
