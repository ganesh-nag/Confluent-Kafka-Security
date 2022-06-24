 package com.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration

@EnableKafka

public class KafkaConfig {

@Value("${spring.kafka.producer.bootstrap-servers}")
private String bootstrapServers;

@Value("${spring.kafka.producer.properties.security.protocol}")
private String securityProtocol;

@Value("${spring.kafka.producer.properties.sasl.mechanism}")
private String saslMechanism;

public Map<String, Object> producerConfigs() {

Map<String, Object> props = new HashMap<>();

props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

//Set the number of retries

//props.put(ProducerConfig.RETRIES_CONFIG, retries);

//A message will be sent when batchSize is reached

//props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);

//Delay time, after the delay time is reached, calculate the size of the batch to send and send the message without reaching it

//props.put(ProducerConfig.LINGER_MS_CONFIG, linger);

//The value of the buffer

//props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);

//Serialization means

props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

//sasl configuration

props.put("security.protocol", securityProtocol);

props.put("sasl.mechanism", saslMechanism);

return props;

}

@Bean

public ProducerFactory<String, String> producerFactory() {

return new DefaultKafkaProducerFactory<>(producerConfigs());

}

@Bean

public KafkaTemplate<String, String> kafkaTemplate() {

return new KafkaTemplate<>(producerFactory());

}
}
