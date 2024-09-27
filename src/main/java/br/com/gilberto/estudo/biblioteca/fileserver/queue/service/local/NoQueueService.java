package br.com.gilberto.estudo.biblioteca.fileserver.queue.service.local;

import br.com.gilberto.estudo.biblioteca.fileserver.queue.service.QueueService;
import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoQueueService implements QueueService {

	@Override
	public void notify(Metadata metadata) {
		log.info("Document-id {} sent to process queue.", metadata.getDocumentId());
	}
	
}
