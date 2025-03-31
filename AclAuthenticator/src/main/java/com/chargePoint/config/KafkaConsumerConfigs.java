package com.chargePoint.config;

import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import java.util.Map;
import java.util.HashMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class KafkaConsumerConfigs {
  @Value("${kafka.consumer.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.consumer.group-id}")
  private String groupId;
  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return props;
  }

  @Bean
  public ConsumerFactory<String, AclAuthenticatorQueuingMessage> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),
        new JsonDeserializer<>(AclAuthenticatorQueuingMessage.class));
  }

  // Creating a Listener
  @Bean
  public ConcurrentKafkaListenerContainerFactory eventListenerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, AclAuthenticatorQueuingMessage> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}