package com.chargePoint.controller;

import com.chargePoint.handlers.SessionHandler;
import com.chargePoint.models.ChargeSessionRequest;
import com.chargePoint.models.ChargeSessionResponse;
import com.chargePoint.util.Constants;
import com.chargePoint.util.ResponseStatus;
import com.chargePoint.util.SessionRequestValidator;
import com.chargePoint.util.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest resource for hosting APIs to handle charge session initialization requests
 */
@RestController
@RequestMapping("/chargeInitialize")
public class ChargeInitializerController {

  private final SessionHandler _sessionHandler;

  @Autowired
    ChargeInitializerController(SessionHandler sessionHandler) {
    _sessionHandler = sessionHandler;
  }

  /**
   * POST endpoint to start a charge session. This API internally validates the request & sends the session
   * request further to the ACL authenticator to further authenticate the request & provide the result back
   * to the provided callback URL.
   */
  @PostMapping("/start")
  public ResponseEntity<Object> initializeChargeSession(@RequestBody ChargeSessionRequest chargeSessionRequest) {

    ValidationResponse validationResponse = SessionRequestValidator.validateRequest(chargeSessionRequest);
    if (!validationResponse.isRequestValid()) {
      return ResponseEntity.badRequest().body(getResponse(ResponseStatus.BAD_REQUEST, validationResponse.getMessage()));
    }

    boolean isHandledSuccessfully = _sessionHandler.handleRequest(chargeSessionRequest);
    return isHandledSuccessfully
        ? ResponseEntity.accepted().body(getResponse(ResponseStatus.ACCEPTED, Constants.SUCCESS_MESSAGE))
        : ResponseEntity.accepted().body(getResponse(ResponseStatus.INTERNAL_ERROR, Constants.ERROR_MESSAGE));
  }

  private ChargeSessionResponse getResponse(ResponseStatus status, String message) {
    return new ChargeSessionResponse(status.toString().toLowerCase(), message);
  }

}
