package br.com.gilberto.estudo.biblioteca.fileserver.repository.local;

import java.io.File;

import com.google.gson.Gson;

import br.com.gilberto.estudo.biblioteca.fileserver.file.local.tools.FileTools;
import br.com.gilberto.estudo.biblioteca.fileserver.model.Metadata;
import br.com.gilberto.estudo.biblioteca.fileserver.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileMetadataRepositoryLocal implements FileMetadataRepository {

	private final File directory;
	
	@Override
	public Metadata create(Metadata fileMetadata) {
		FileTools.store(fileMetadata, directory, fileMetadata.getDocumentId());
		return fileMetadata;
	}

	@Override
	public Metadata update(Metadata fileMetadata) {
		FileTools.store(fileMetadata, directory, fileMetadata.getDocumentId());
		return fileMetadata;
	}

	@Override
	public Metadata findById(String documentid) {
		var json = FileTools.loadTxt(directory,documentid);
		if(json == null) {
			return null;
		}
		return new Gson().fromJson(json, Metadata.class);
	}

}
