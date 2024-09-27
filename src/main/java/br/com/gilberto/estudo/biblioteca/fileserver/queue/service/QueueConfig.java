package br.com.gilberto.estudo.biblioteca.fileserver.queue.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gilberto.estudo.biblioteca.fileserver.config.ApplicationConfig;
import br.com.gilberto.estudo.biblioteca.fileserver.queue.service.local.NoQueueService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class QueueConfig {

	private final ApplicationConfig parameters;
	
	
	@Bean
	QueueService queueService() {
		if("NoQueueService".equalsIgnoreCase(this.parameters.getQueueService())) {
			return new NoQueueService();
		}
		throw new IllegalArgumentException("Queue service " + this.parameters.getQueueService() + " not implemented.");
		
	}
	
}
