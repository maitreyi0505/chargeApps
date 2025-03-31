package com.chargePoint.handlers;

import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import com.chargePoint.models.ChargeSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionHandler {

  private final AclAuthenticatorQueuingMessageProducer _aclAuthenticatorQueuingMessageProducer;
  private final Logger _logger = LoggerFactory.getLogger(SessionHandler.class);

  @Autowired
  SessionHandler(AclAuthenticatorQueuingMessageProducer aclAuthenticatorQueuingMessageProducer) {
    this._aclAuthenticatorQueuingMessageProducer = aclAuthenticatorQueuingMessageProducer;
  }

  /**
   * Build & fire the {@link AclAuthenticatorQueuingMessage} event for handling charge sessionHandler.
   * @return boolean response for successful production of AAQM event
   */
  public boolean handleRequest(ChargeSessionRequest chargeSessionRequest) {
    AclAuthenticatorQueuingMessage aaqm =
        new AclAuthenticatorQueuingMessage(chargeSessionRequest.getStationId(), chargeSessionRequest.getDriverId(),
            chargeSessionRequest.getCallbackUrl());

    try {
      _aclAuthenticatorQueuingMessageProducer.sendAAQMEvent(aaqm);
      _logger.info("Successfully produced the following AclAuthenticatorQueuingMessage: {}", aaqm.toString());
    } catch (Exception ex) {
      _logger.error("Failed to produce the following AclAuthenticatorQueuingMessage: {}", aaqm.toString());
      return false;
    }
    return true;
  }

}
