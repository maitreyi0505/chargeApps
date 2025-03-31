package com.chargePoint.handler;

import com.chargePoint.dataManager.ChargeSessionRequestDAO;
import com.chargePoint.models.AclAuthenticatorQueuingMessage;
import com.chargePoint.models.ChargeSessionAcl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AclAuthenticatorQueuingMessageHandler {
  public AclAuthenticatorQueuingMessageHandler() {
    System.out.println("Hello this is loggg");
  }

  @Autowired
  private ChargeSessionRequestDAO chargeSessionRequestDAO;

//  @Autowired
  @KafkaListener(topics = "AclAuthenticatorQueuingMessage",
      containerFactory = "eventListenerFactory"
      )
  public void consume(AclAuthenticatorQueuingMessage aaqm) {
    System.out.println("Received AAQM Event: " + aaqm);

//    ChargeSessionRequestDAO chargeSessionRequestDAO = new ChargeSessionRequestDAOImpl();
    ChargeSessionAcl chargeSessionAcl = new ChargeSessionAcl();
    chargeSessionAcl.setEntityId(aaqm.getDriverId());
    chargeSessionAcl.setEntityType("DRIVER");
    chargeSessionAcl.setAccess("ALLOWED");
    chargeSessionRequestDAO.createChargeSessionAcls(chargeSessionAcl);
    System.out.println(chargeSessionRequestDAO.getChargeSessionAcls().size());
    chargeSessionRequestDAO.getChargeSessionAcls().forEach(acl -> {
      System.out.println(acl.toString());
    });
  }
}