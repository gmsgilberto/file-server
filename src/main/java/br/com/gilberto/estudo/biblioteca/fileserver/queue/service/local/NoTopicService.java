package br.com.gilberto.estudo.biblioteca.fileserver.queue.service.local;

import br.com.gilberto.estudo.biblioteca.fileserver.queue.service.TopicService;
import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoTopicService implements TopicService {

	@Override
	public void publish(Metadata metadata) {
		log.info("There is not TOPIC to notify listeners.");
	}
	
}
