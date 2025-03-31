package com.chargePoint.controller;

import com.chargePoint.models.ChargeSessionRequest;
import com.chargePoint.models.ChargeSessionResponse;
import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import com.chargePoint.eventProducer.AclAuthenticatorQueuingMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chargeInitialize")
public class ChargeInitializerController {

  private final AclAuthenticatorQueuingMessageProducer aclAuthenticatorQueuingMessageProducer;

  @Autowired
  public ChargeInitializerController(AclAuthenticatorQueuingMessageProducer aclAuthenticatorQueuingMessageProducer) {
    this.aclAuthenticatorQueuingMessageProducer = aclAuthenticatorQueuingMessageProducer;
  }

  // POST endpoint to start a charge session
  @PostMapping("/start")
  public ResponseEntity<Object> initializeChargeSession(@RequestBody ChargeSessionRequest chargeSessionRequest) {

    aclAuthenticatorQueuingMessageProducer.sendAAQMEvent(
        new AclAuthenticatorQueuingMessage(chargeSessionRequest.getStationId(), chargeSessionRequest.getDriverId(),
            chargeSessionRequest.getCallbackUrl()));

    return ResponseEntity.accepted()
        .body(new ChargeSessionResponse("accepted",
            "Request is being processed asynchronously. The result will be sent to the provided callback URL."));
  }

}
