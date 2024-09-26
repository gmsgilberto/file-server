package br.com.gilberto.estudo.biblioteca.fileserver.controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gilberto.estudo.biblioteca.fileserver.controllers.response.FileMetadataResponse;
import br.com.gilberto.estudo.biblioteca.fileserver.services.FileUploadService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE  )
@AllArgsConstructor
public class FileUploadController {

	
	private FileUploadService fileUploadService;
	
	@PostMapping
	public ResponseEntity<FileMetadataResponse> upload(@RequestParam MultipartFile file) throws IOException{
		var fileMetadata = this.fileUploadService.execute(file.getOriginalFilename(), "gms", file.getBytes());
		return ResponseEntity
					.status(201)
					.body(new FileMetadataResponse(fileMetadata));
	}
	
	
}
