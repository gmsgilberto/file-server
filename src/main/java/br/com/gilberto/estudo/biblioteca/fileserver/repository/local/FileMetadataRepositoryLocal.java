package br.com.gilberto.estudo.biblioteca.fileserver.repository.local;

import java.io.File;

import com.google.gson.Gson;

import br.com.gilberto.estudo.biblioteca.fileserver.file.local.tools.FileTools;
import br.com.gilberto.estudo.biblioteca.fileserver.model.FileMetadata;
import br.com.gilberto.estudo.biblioteca.fileserver.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileMetadataRepositoryLocal implements FileMetadataRepository {

	private final File directory;
	
	@Override
	public FileMetadata create(FileMetadata fileMetadata) {
		FileTools.store(fileMetadata, directory, fileMetadata.getDocumentId());
		return fileMetadata;
	}

	@Override
	public FileMetadata update(FileMetadata fileMetadata) {
		FileTools.store(fileMetadata, directory, fileMetadata.getDocumentId());
		return fileMetadata;
	}

	@Override
	public FileMetadata findById(String documentid) {
		var json = FileTools.loadTxt(directory,documentid);
		if(json == null) {
			return null;
		}
		return new Gson().fromJson(json, FileMetadata.class);
	}

}
