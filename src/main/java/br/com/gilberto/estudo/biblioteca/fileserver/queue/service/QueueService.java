package br.com.gilberto.estudo.biblioteca.fileserver.queue.service;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;

public interface QueueService {

	/**
	 * Notifica a fila de mensagems
	 * @param processId
	 * @param documentId
	 */
	void notify(Metadata metadata);
	
}
