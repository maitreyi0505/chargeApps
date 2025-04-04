package com.chargePoint.handler;

import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AclAuthenticatorQueuingMessageConsumer {

  private static final Logger _log = LoggerFactory.getLogger(AclAuthenticatorQueuingMessageConsumer.class);

  @Autowired
  AuthenticationRequestHandler _authenticationRequestHandler;

  /**
   * AAQM Consumer: Consumes event & triggers authentication request handler
   */
  @KafkaListener(topics = "AclAuthenticatorQueuingMessage", containerFactory = "eventListenerFactory")
  public void consume(AclAuthenticatorQueuingMessage aaqm) {
    _log.info("Received AAQM Event: {}", aaqm);
    _authenticationRequestHandler.handleAclRequest(aaqm.getDriverId(), aaqm.getStationId(), aaqm.getCallbackUrl(),
        aaqm.getTimestamp());
  }
}
