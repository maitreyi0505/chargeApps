package com.chargePoint.handler;

import com.chargePoint.models.CallbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CallbackHandler {

  @Autowired
  RestTemplate _restTemplate;

  // Makes a call to the callback URL & returns the status code to be logged in audit table
  public int sendResponseToCallback(String callbackUrl, CallbackRequest callbackRequest) {
    ResponseEntity<Void> responseEntity = _restTemplate.postForEntity(callbackUrl,callbackRequest, Void.class);
    return responseEntity.getStatusCode().value();
  }
}
