package br.com.gilberto.estudo.biblioteca.fileserver.queue.service;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;

public interface TopicService {

	
	/**
	 * Notifica a fila de mensagems
	 * @param processId
	 * @param documentId
	 */
	void publish(Metadata metadata);
	
}
