package br.com.gilberto.estudo.biblioteca.fileserver.services.local;

import java.io.File;
import java.io.FileOutputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileStorageException;
import br.com.gilberto.estudo.biblioteca.fileserver.services.FileStorageService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileStorageServiceLocal implements FileStorageService {
	
	private final File directory;

	@Override
	public void store(String fileId, byte[] content) {
		
		var dest = new File(directory, fileId);
		try (var out = new FileOutputStream(dest)) {
			out.write(content);
			out.flush();
		}catch (Exception e) {
			throw new FileStorageException("Nao foi possivel gravar o arquivo  "+ fileId, e);
		}
	}

}
