package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
	
	String kafkaPath = "classpath:kafka_client_jaas.conf";
        System.setProperty("java.security.auth.login.config", kafkaPath);
        
		SpringApplication.run(KafkaApplication.class,args);

	}

}
