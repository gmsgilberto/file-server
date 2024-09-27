package br.com.gilberto.estudo.biblioteca.fileserver.store.service.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileServerException;
import br.com.gilberto.estudo.biblioteca.fileserver.store.service.StorageService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileStorageServiceLocal implements StorageService {
	
	private final File directory;

	@Override
	public void store(String fileId, byte[] content) {
		
		var dest = new File(directory, fileId);
		try (var out = new FileOutputStream(dest)) {
			out.write(content);
			out.flush();
		}catch (Exception e) {
			throw new FileServerException("Nao foi possivel gravar o arquivo  "+ fileId, e);
		}
	}

	@Override
	public InputStream findById(String fileId) {

		try {
			var dest = new File(directory, fileId);
			return dest.exists() ? new FileInputStream(dest) : null;
		}catch (Exception e) {
			throw new FileServerException("Falha ao tentar fazer o download do arquivo " + fileId, e);
		} 
		
	}
}
