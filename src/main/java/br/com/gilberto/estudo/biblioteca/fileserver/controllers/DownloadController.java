package br.com.gilberto.estudo.biblioteca.fileserver.controllers;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gilberto.estudo.biblioteca.fileserver.model.Metadata;
import br.com.gilberto.estudo.biblioteca.fileserver.services.DownloadService;
import br.com.gilberto.estudo.biblioteca.fileserver.services.model.CustomInputStream;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "v1/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
@AllArgsConstructor
public class DownloadController {

	private DownloadService service;
	
	@GetMapping(path = "/{document-id}")
	public ResponseEntity<InputStreamResource> download(@PathVariable(name = "document-id", required = true) String documentId) throws IOException{
		var resource = this.service.execute(documentId);
		return resource == null ? 
				ResponseEntity.notFound().build() : 
				buildResponse(resource);
	}

	private ResponseEntity<InputStreamResource> buildResponse(CustomInputStream resource) {
		return new ResponseEntity<InputStreamResource>(
				new InputStreamResource(resource.getInputStream()),
				headers(resource.getFileMetadata()), 
				HttpStatus.OK);
	}

	private MultiValueMap<String, String> headers(Metadata metadata) {
		var headers = new LinkedMultiValueMap<String, String>();
		headers.set("Content-disposition", "inline; filename="+ metadata.getFileName());
		return headers;
	}
	
}
