package com.chargePoint.eventProducer;

import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class AclAuthenticatorQueuingMessageProducer {

  private final KafkaTemplate<String, AclAuthenticatorQueuingMessage> kafkaTemplate;
  private final String topic;

  @Autowired
  public AclAuthenticatorQueuingMessageProducer(KafkaProducerConfig kafkaProducerConfig,
      @Qualifier("kafkaTemplate") KafkaTemplate<String, AclAuthenticatorQueuingMessage> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = kafkaProducerConfig.getTopic();
  }

  public void sendAAQMEvent(AclAuthenticatorQueuingMessage aaqm) {
    kafkaTemplate.send(topic, aaqm);
  }
}