package br.com.gilberto.estudo.biblioteca.fileserver.controllers.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import lombok.Getter;

@Getter
public class FileMetadataResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String documentId;
	private final String fileName;
	private final String userid;
	private final String length;
	private final String creationTime;
	private boolean sucess;

	public FileMetadataResponse(FileMetadata entity) {
		this.documentId = entity.getDocumentId();
		this.sucess = entity.isSucess();
		this.fileName = entity.getFileName();
		this.userid = entity.getUserid();
		this.length = new BigDecimal(entity.getLength() / 1024.00 / 1024.00).setScale(3, RoundingMode.HALF_UP) + "KB"; 
		this.creationTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSS").format(entity.getCreationTime());
	}
	

}
