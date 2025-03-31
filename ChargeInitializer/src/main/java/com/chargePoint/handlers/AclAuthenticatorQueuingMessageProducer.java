package com.chargePoint.handlers;

import com.chargePoint.config.KafkaProducerConfig;
import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class AclAuthenticatorQueuingMessageProducer {

  private final KafkaTemplate<String, AclAuthenticatorQueuingMessage> _kafkaTemplate;
  private final String topic;

  @Autowired
  public AclAuthenticatorQueuingMessageProducer(KafkaProducerConfig kafkaProducerConfig,
      @Qualifier("kafkaTemplate") KafkaTemplate<String, AclAuthenticatorQueuingMessage> kafkaTemplate) {
    this._kafkaTemplate = kafkaTemplate;
    this.topic = kafkaProducerConfig.getTopic();
  }

  public void sendAAQMEvent(AclAuthenticatorQueuingMessage aaqm)
      throws ExecutionException, InterruptedException, TimeoutException {
      _kafkaTemplate.send(topic, aaqm.getDriverId(), aaqm).get(2, TimeUnit.SECONDS);
  }

}