package br.com.gilberto.estudo.biblioteca.fileserver.queue.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gilberto.estudo.biblioteca.fileserver.configuration.ApplicationConfig;
import br.com.gilberto.estudo.biblioteca.fileserver.queue.service.local.NoTopicService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class TopicServiceConfig {

	private final ApplicationConfig parameters;
	
	
	@Bean
	TopicService topicService() {
		if("NoQueueService".equalsIgnoreCase(this.parameters.getQueueService())) {
			return new NoTopicService();
		}
		throw new IllegalArgumentException("Topic Service " + this.parameters.getQueueService() + " not implemented.");
		
	}
	
}
