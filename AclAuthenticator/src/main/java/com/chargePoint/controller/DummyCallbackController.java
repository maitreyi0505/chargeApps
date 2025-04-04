package com.chargePoint.controller;

import com.chargePoint.models.CallbackRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Dummy Rest resource for hosting a callback POST request
 */
@RestController
@RequestMapping("/dummyCallback")
public class DummyCallbackController {

  private final Logger _logger = LoggerFactory.getLogger(DummyCallbackController.class);

  @PostMapping("/post")
  public ResponseEntity<Object> postCallback(@RequestBody CallbackRequest callbackRequest) {
    _logger.info("Received request on dummy callback URL " + callbackRequest.toString());
    // Dummy logic to return different status
    if (callbackRequest.getStationId().length() % 2 == 0) {
      return ResponseEntity.status(201).build();
    } else {
      return ResponseEntity.status(500).build();
    }
  }
}
