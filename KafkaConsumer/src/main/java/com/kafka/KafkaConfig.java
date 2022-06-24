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

@Value("${spring.kafka.consumer.bootstrap-servers}")
private String bootstrapServers;

@Value("${spring.kafka.consumer.enable-auto-commit}")
private Boolean autoCommit;

@Value("${spring.kafka.consumer.auto-offset-reset}")
private String autoOffsetReset;

@Value("${spring.kafka.consumer.group-id}")
private String groupId;


@Value("${spring.kafka.consumer.properties.security.protocol}")
private String securityProtocol;

@Value("${spring.kafka.consumer.properties.sasl.mechanism}")
private String saslMechanism;


@Bean
public Map<String, Object> consumerConfigs() {

Map<String, Object> props = new HashMap<String, Object>();

props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);

props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

//sasl configuration
props.put("security.protocol", securityProtocol);
props.put("sasl.mechanism", saslMechanism);

return props;

}

@Bean

public KafkaListenerContainerFactory<?> batchFactory() {

ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));

return factory;

}

}
