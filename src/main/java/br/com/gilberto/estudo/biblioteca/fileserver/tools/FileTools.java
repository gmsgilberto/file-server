package br.com.gilberto.estudo.biblioteca.fileserver.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.gson.Gson;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileServerException;

public class FileTools {

	
	public static final void store(Serializable object, File directory, String fileName) {
		
		if(object == null) {
			throw new IllegalArgumentException("O Objeto a ser serializado nao pode ser nulo");
		}
		store(
				new Gson().toJson(object).getBytes(), 
				directory, 
				fileName
			);
	}
	
	
	public static final void store(byte[] content, File directory, String fileName) {
		try (var out = new FileOutputStream(new File(directory, fileName))) {
			out.write(content);
			out.flush();
		}catch (Exception e) {
			throw new FileServerException("Nao foi possivel gravar o arquivo  "+ fileName, e);
		}
	}


    public static byte[] zip(String filename, byte[] content) throws IOException {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        ZipEntry zipEntry = new ZipEntry(filename);
        zipOut.putNextEntry(zipEntry);
        zipOut.write(content, 0, content.length);
        zipOut.close();
        fos.close();
        return fos.toByteArray();
    }	
	
	
	
}
