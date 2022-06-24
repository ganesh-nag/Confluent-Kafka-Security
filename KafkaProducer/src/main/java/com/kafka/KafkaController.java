package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	
	@Autowired
	MessageProducer producer;
	
	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		producer.send(message);

		return "Message sent to the Kafka Topic TOPIC1 Successfully " + message;
	}

}
