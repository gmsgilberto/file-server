package br.com.gilberto.estudo.biblioteca.fileserver.download.controler;

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

import br.com.gilberto.estudo.biblioteca.fileserver.download.usecase.DownloadUsecase;
import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "v1/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
@AllArgsConstructor
public class DownloadResource {

	private DownloadUsecase usecase;
	
	@GetMapping(path = "/{document-id}")
	public ResponseEntity<InputStreamResource> download(@PathVariable(name = "document-id", required = true) String documentId) throws IOException{
		var resource = this.usecase.execute(documentId);
		return resource == null ? 
				ResponseEntity.notFound().build() : 
					new ResponseEntity<InputStreamResource>(
						new InputStreamResource(resource.getInputStream()),
						headers(resource.getFileMetadata()), 
						HttpStatus.OK
						);
	}

	private MultiValueMap<String, String> headers(Metadata metadata) {
		var headers = new LinkedMultiValueMap<String, String>();
		headers.set("Content-disposition", "inline; filename="+ metadata.getFileName());
		return headers;
	}
	
}
