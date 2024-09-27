package br.com.gilberto.estudo.biblioteca.fileserver.controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gilberto.estudo.biblioteca.fileserver.config.ApplicationConfig;
import br.com.gilberto.estudo.biblioteca.fileserver.controllers.response.MetadataResponse;
import br.com.gilberto.estudo.biblioteca.fileserver.usecases.UploadUseCase;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "v1/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE  )
@AllArgsConstructor
public class UploadController {

	private UploadUseCase fileUploadService;
	private ApplicationConfig configuration;
	
	@PostMapping
	public ResponseEntity<MetadataResponse> upload(@RequestParam MultipartFile file, 
			                                       @RequestHeader(name = "process-id", required = true) String processid) throws IOException{
		
		
		var fileMetadata = this.fileUploadService
									.execute(
											file.getOriginalFilename(), 
											processid, 
											file.getBytes(),
											configuration.isZip()
									);
		return ResponseEntity
					.status(201)
					.body(new MetadataResponse(fileMetadata));
	}
	
	
}
