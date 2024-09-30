package br.com.gilberto.estudo.biblioteca.fileserver.upload.out;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import lombok.Getter;

@Getter
public class MetadataResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String documentId;
	private final String fileName;
	private final String originalfileName;
	private final String processid;
	private final String size;
	private final String creationDate;
	private final String updateDate;
	private boolean sucess;

	public MetadataResponse(Metadata entity) {
		this.documentId = entity.getDocumentId();
		this.sucess = entity.isSucess();
		this.fileName = entity.getFileName();
		this.originalfileName = entity.getOriginalFileName(); 
		this.processid = entity.getProcessid();
		this.size = entity.getSize() +  " bytes";
		var sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSS");
		this.creationDate = sdf.format(entity.getCreationTime());
		this.updateDate = sdf.format(entity.getUpdateTime());
	}
	

}
